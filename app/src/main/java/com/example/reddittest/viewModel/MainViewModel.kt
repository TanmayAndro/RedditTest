package com.example.reddittest.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.reddittest.dc.CommentsItem
import com.example.reddittest.dc.Posts
import com.example.reddittest.dc.Resource
import com.example.reddittest.utils.NetworkHelper
import com.squareup.moshi.JsonDataException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper,
) : ViewModel() {

    var commentsLink: String = ""
    private val _posts = MutableLiveData<Resource<Posts>>()
    private val _comments = MutableLiveData<Resource<List<CommentsItem>>>()
    val posts: LiveData<Resource<Posts>>
        get() = _posts
    val comments: LiveData<Resource<List<CommentsItem>>>
        get() = _comments

    @OptIn(DelicateCoroutinesApi::class)
    fun fetchPosts(feed_type : String){
        GlobalScope.launch {
            _posts.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                mainRepository.getPosts(feed_type).let {
                    if (it.isSuccessful) {
                        _posts.postValue(Resource.success(it.body()))
                    } else _posts.postValue(Resource.error(it.errorBody().toString(), null))
                }
            } else _posts.postValue(Resource.error("No internet connection", null))
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun fetchComments(url : String){
        try {
            GlobalScope.launch {
                _comments.postValue(Resource.loading(null))
                if (networkHelper.isNetworkConnected()) {
                    mainRepository.getComments(url + ".json").let {
                        if (it.isSuccessful) {
                            _comments.postValue(Resource.success(it.body()))
                        } else _comments.postValue(Resource.error(it.errorBody().toString(), null))
                    }
                } else _comments.postValue(Resource.error("No internet connection", null))
            }
        }catch (e : JsonDataException){
            Log.d("JsonDataException", e.toString())
        }
    }

    fun setcommentsLink(link : String){
        this.commentsLink = link
    }

}