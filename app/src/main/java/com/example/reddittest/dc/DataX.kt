package com.example.reddittest.dc

import com.squareup.moshi.Json

data class DataX(
    @Json(name = "id")
    val id: String,

    @Json(name = "permalink")
    val permalink: String,

    @Json(name = "thumbnail")
    val thumbnail: String,

    @Json(name = "title")
    val title: String,

    @Json(name = "url")
    val url: String,

)