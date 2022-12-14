package com.rifqipadisiliwangi.dreamacademy_bootcamp.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RegisterResult(
    val id: Int? = null,
    val token: String? = null,
)
