package com.example.reddittest.api

import com.example.reddittest.dc.Comments
import com.example.reddittest.dc.CommentsItem
import com.example.reddittest.dc.Posts
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {

    override suspend fun getPosts(feed_type: String): Response<Posts> = apiService.getPosts(feed_type)
    override suspend fun getComments(url: String): Response<List<CommentsItem>> = apiService.getComments(url)

}