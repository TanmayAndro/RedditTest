package com.example.reddittest.dc


import com.squareup.moshi.Json

data class Children(
    @Json(name = "data")
    val data: DataX,
    @Json(name = "kind")
    val kind: String
)