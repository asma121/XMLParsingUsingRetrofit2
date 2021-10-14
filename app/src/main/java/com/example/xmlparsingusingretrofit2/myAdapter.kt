package com.example.xmlparsingusingretrofit2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_row.view.*

class myAdapter(private val entries:ArrayList<ArrayList<String>>): RecyclerView.Adapter<myAdapter.ItemViewHolder>() {
    class ItemViewHolder(itemView : View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_row,
                parent,
                false
            )
        )
    }
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val name= entries[position][0]
        val name2= entries[position][1]

        holder.itemView.apply {
            textView.text= name
            textView2.text= name2


        }
    }

    override fun getItemCount()=entries.size
}