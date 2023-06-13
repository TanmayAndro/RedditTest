package com.example.reddittest.dc


import com.squareup.moshi.Json

data class DataXX(

    @Json(name = "children")
    val children: List<ChildrenX>,

)