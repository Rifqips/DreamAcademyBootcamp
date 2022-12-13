package com.rifqipadisiliwangi.dreamacademy_bootcamp.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MakeUp(
    val brand: String?,
    val name: String?,
    val price: String?,
    var priceSign: String?,
    val imageLink: String?,
)