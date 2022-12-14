package com.rifqipadisiliwangi.dreamacademy_bootcamp.data.network.api

import androidx.viewbinding.BuildConfig
import com.rifqipadisiliwangi.dreamacademy_bootcamp.data.model.*
import com.rifqipadisiliwangi.dreamacademy_bootcamp.data.network.ResponseStatus
import com.rifqipadisiliwangi.dreamacademy_bootcamp.data.network.deserializeJson
import com.rifqipadisiliwangi.dreamacademy_bootcamp.data.network.mapFailedResponse
import com.rifqipadisiliwangi.dreamacademy_bootcamp.data.network.serialized
import okhttp3.*
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import java.io.IOException
import java.util.concurrent.TimeUnit

class UserApi {
    private val usersEndpoint = "/users"
    fun getUser(onResponse: (ResponseStatus<UserPagination>) -> Unit) {
        NetworkClient
            .client
            .newCall(
                NetworkClient.requestBuilder(usersEndpoint)
            )
            .enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    onResponse.invoke(ResponseStatus.Failed(1, e.message.toString(), e))
                }

                override fun onResponse(call: Call, response: Response) {
                    if (response.isSuccessful) {
//                        val body = JSONObject(response.body?.string() ?: "")
                        val data = deserializeJson<UserPagination>(response.body?.string() ?: "")
                        data?.let {
                            onResponse.invoke(
                                ResponseStatus.Success(data)
                            )
                        }
                    } else {
                        onResponse.invoke(
                            mapFailedResponse(response)
                        )
                    }
                }
            })
    }

    fun getUserPagination(pages: Int = 1, onResponse: (ResponseStatus<List<User>>) -> Unit) {
        val endpoint = "$usersEndpoint${if (pages > 1) "?page=$pages" else ""}"
        val request = NetworkClient.requestBuilder(endpoint)
        NetworkClient
            .client
            .newCall(request)
            .enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    onResponse.invoke(
                        ResponseStatus.Failed(
                            code = -1,
                            message = e.message.toString(),
                            throwable = e
                        )
                    )
                }

                override fun onResponse(call: Call, response: Response) {
                    if (response.isSuccessful) {
                        val userPagination = deserializeJson<UserPagination>(response.body?.string() ?: "") ?: UserPagination()
                        onResponse.invoke(
                            ResponseStatus.Success(
                                data = userPagination.data,
                                method = "GET",
                                status = true
                            )
                        )
                    } else {
                        onResponse.invoke(
                            mapFailedResponse(response)
                        )
                    }
                    response.body?.close()
                }
            })
    }

    fun getError(onResponse: (ResponseStatus<Nothing>) -> Unit) {
        NetworkClient
            .client
            .newCall(NetworkClient.requestBuilder("/unknown/23"))
            .enqueue(object: Callback {
                override fun onFailure(call: Call, e: IOException) {
                    onResponse.invoke(ResponseStatus.Failed(-1, e.message.toString(), e))
                }

                override fun onResponse(call: Call, response: Response) {
                    onResponse.invoke(ResponseStatus.Failed(-1, response.message))
                    response.body?.close()
                }
            })
    }

    fun addUser(data: AddUserModel, onResponse: (ResponseStatus<AddUserResponse>) -> Unit) {
        val request = NetworkClient
            .requestBuilder(usersEndpoint, NetworkClient.METHOD.POST, data.serialized())
        NetworkClient
            .client
            .newCall(request)
            .enqueue(object: Callback {
                override fun onFailure(call: Call, e: IOException) {
                    onResponse.invoke(
                        ResponseStatus.Failed(1, e.message.toString(), e)
                    )
                }

                override fun onResponse(call: Call, response: Response) {
                    if (response.isSuccessful) {
                        onResponse.invoke(
                            ResponseStatus.Success(
                                AddUserResponse(
                                    JSONObject(
                                        response.body?.string().toString()
                                    )
                                )
                            )
                        )
                    } else {
                        onResponse.invoke(
                            ResponseStatus.Failed(response.code, "Failed")
                        )
                    }

                    response.body?.close()
                }
            })
    }

    companion object {
        private const val BASE_URL = "https://reqres.in/api"
    }

}