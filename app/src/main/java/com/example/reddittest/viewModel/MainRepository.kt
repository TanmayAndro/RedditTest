package com.example.reddittest.viewModel

import com.example.reddittest.api.ApiHelper
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiHelper: ApiHelper) {
    suspend fun getPosts(feed_type: String) =  apiHelper.getPosts(feed_type)
    suspend fun getComments(url: String) =  apiHelper.getComments(url)
}