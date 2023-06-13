package com.example.reddittest.dc


import com.squareup.moshi.Json

data class ChildrenX(
    @Json(name = "data")
    val `data`: DataXXX,
    @Json(name = "kind")
    val kind: String
)