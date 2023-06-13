package com.example.reddittest.api

import com.example.reddittest.dc.Comments
import com.example.reddittest.dc.CommentsItem
import com.example.reddittest.dc.Posts
import retrofit2.Response

interface ApiHelper {
    suspend fun getPosts(feed_type : String): Response<Posts>
    suspend fun getComments(url: String): Response<List<CommentsItem>>
}