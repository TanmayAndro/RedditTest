package com.example.reddittest.dc


import com.squareup.moshi.Json

data class Data(
    @Json(name = "after")
    val after: String,

    @Json(name = "children")
    val children: List<Children>,

)