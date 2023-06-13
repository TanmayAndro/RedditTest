package com.example.reddittest.api

import com.example.reddittest.dc.Comments
import com.example.reddittest.dc.CommentsItem
import com.example.reddittest.dc.Posts
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url




interface ApiService {

    @GET("r/{feed_type}/.json")
    suspend fun getPosts(@Path(value = "feed_type", encoded = true) feed_type : String): Response<Posts>

    @GET
    suspend fun getComments(@Url url: String?): Response<List<CommentsItem>>
}