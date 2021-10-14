package com.example.xmlparsingusingretrofit2

import retrofit2.Call
import retrofit2.http.GET

interface FeedAPI {
    @get:GET("food/.rss")
    val feed: Call<Feed?>?

    companion object {
        const val BASE_URL = "https://www.reddit.com/r/"
    }
}