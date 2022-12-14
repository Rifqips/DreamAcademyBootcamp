package com.rifqipadisiliwangi.dreamacademy_bootcamp.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AddUserModel(
    var name : String,
    var job : String
)
