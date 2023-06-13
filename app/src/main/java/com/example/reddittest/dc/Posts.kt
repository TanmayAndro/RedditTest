package com.example.reddittest.dc


import com.squareup.moshi.Json

data class Posts(
    @Json(name = "data")
    val `data`: Data,
    @Json(name = "kind")
    val kind: String
)