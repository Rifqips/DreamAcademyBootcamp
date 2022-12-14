package com.rifqipadisiliwangi.dreamacademy_bootcamp.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginRegisterModel(
    val email: String?,
    val password: String?
)
