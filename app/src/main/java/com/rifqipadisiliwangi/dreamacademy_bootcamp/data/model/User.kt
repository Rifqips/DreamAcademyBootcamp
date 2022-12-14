package com.rifqipadisiliwangi.dreamacademy_bootcamp.data.model

import com.rifqipadisiliwangi.dreamacademy_bootcamp.data.network.getIntData
import com.rifqipadisiliwangi.dreamacademy_bootcamp.data.network.getStringData
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import org.json.JSONObject

@JsonClass(generateAdapter = true)
data class User(
    val id: Int?,
    val email: String,
    @Json(name = "first_name")
    val firstName: String,
    @Json(name = "last_name")
    val lastName: String,
    val avatar: String
) {
}
