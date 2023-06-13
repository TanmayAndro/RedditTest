package com.example.reddittest.dc


import com.squareup.moshi.Json

data class CommentsItem(
    @Json(name = "data")
    val `data`: DataXX,
    @Json(name = "kind")
    val kind: String
)