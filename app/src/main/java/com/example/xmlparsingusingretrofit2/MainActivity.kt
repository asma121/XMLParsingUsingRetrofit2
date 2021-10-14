package com.example.xmlparsingusingretrofit2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

class MainActivity : AppCompatActivity() {
    private  val TAG = "MainActivity"
    private  val BASE_URL = "https://www.reddit.com/r/"
    lateinit var rvmain:RecyclerView
    lateinit var button: Button
    lateinit var en:ArrayList<ArrayList<String>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvmain=findViewById(R.id.rvmain)
        button=findViewById(R.id.button2)
        en=ArrayList()


        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .build()
        val feedAPI = retrofit.create(FeedAPI::class.java)
        val call = feedAPI.feed

        button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {

                call!!.enqueue(object : Callback<Feed?> {
                    override fun onResponse(call: Call<Feed?>, response: Response<Feed?>) {
                        Log.d(TAG, "onResponse: feed: " + response.body().toString())
                        Log.d(TAG, "onResponse: Server Response: $response")
                        val entries = response.body()!!.entrys
                        for (entry in entries!!) {
                            en+= arrayListOf(arrayListOf("${entry.title}","${entry.author?.name}"))
                        }
                        rvmain.adapter=myAdapter(en)
                        rvmain.layoutManager= LinearLayoutManager(this@MainActivity)
                    }

                    override fun onFailure(call: Call<Feed?>, t: Throwable) {
                        Log.e(TAG, "onFailure: Unable to retrieve RSS: " + t.message)
                        Toast.makeText(this@MainActivity, "An Error Occured", Toast.LENGTH_SHORT).show()
                    }
                })

            }

        })

    }

}
