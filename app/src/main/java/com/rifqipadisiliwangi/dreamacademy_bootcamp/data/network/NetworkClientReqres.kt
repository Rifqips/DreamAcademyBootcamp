package com.rifqipadisiliwangi.dreamacademy_bootcamp.data.network

import androidx.viewbinding.BuildConfig
import com.rifqipadisiliwangi.dreamacademy_bootcamp.data.network.NetworkClientReqres.Companion.BASE_URL
import com.rifqipadisiliwangi.dreamacademy_bootcamp.data.network.NetworkClientReqres.Companion.client
import okhttp3.*
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException
import java.util.concurrent.TimeUnit

class NetworkClientReqres {
    companion object {
        const val BASE_URL = "https://reqres.in/api"
        private val headerInterceptor: Interceptor = Interceptor {
            val request = it.request().newBuilder()
            request
                .addHeader("content-type","application-json")

            return@Interceptor it.proceed(request.build())
        }

        val client : OkHttpClient by lazy {
            OkHttpClient
                .Builder()
                .addInterceptor(headerInterceptor)
                .addInterceptor(
                    HttpLoggingInterceptor().apply {
                        level =
                            if (BuildConfig.DEBUG)
                                HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
                    }
                )
                .callTimeout(timeout = 5L, unit = TimeUnit.SECONDS)
                .connectTimeout(timeout = 2L, unit = TimeUnit.SECONDS)
                .build()
        }

        fun requestBuilder(endpoint: String, method: METHOD = METHOD.GET, jsonBody: String? = null): Request {
            val request = Request
                .Builder()
                .url("$BASE_URL$endpoint")

            if (jsonBody != null)
                request.method(method.name, jsonBody.toRequestBody())


            return request.build()
        }
    }
}

enum class METHOD {
    GET,
    POST
}

fun getAsync(endpoint: String, callback: Callback) {
    val request = Request
        .Builder()
        .url("$BASE_URL$endpoint")
        .build()

    client.newCall(request = request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            callback.onFailure(call, e)
        }

        override fun onResponse(call: Call, response: Response) {
            callback.onResponse(call, response)
            response.body?.close()

        }
    })
}