package com.example.reddittest.dc


import com.squareup.moshi.Json

data class DataXXX(

    @Json(name = "author")
    val author: String?,

    @Json(name = "body")
    val body: String?,

    @Json(name = "thumbnail")
    val thumbnail: String?,

    @Json(name = "title")
    val title: String?,

)