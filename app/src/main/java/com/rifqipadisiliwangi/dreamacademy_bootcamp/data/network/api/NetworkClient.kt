package com.rifqipadisiliwangi.dreamacademy_bootcamp.data.network.api

import androidx.viewbinding.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

object NetworkClient {
    val baseUrl = "http://makeup-api.herokuapp.com/api"
    val productEndpoint = "/v1/products.json"

    private val headerInterceptor: Interceptor = Interceptor {
        val req = it.request().newBuilder()

        req.addHeader("Content-Type", "application/json")

        return@Interceptor it.proceed(req.build())
    }

    val client: OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(headerInterceptor)
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level =
                        if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
                }
            )
            .callTimeout(timeout = 25L, unit = TimeUnit.SECONDS)
            .connectTimeout(timeout = 25L, unit = TimeUnit.SECONDS)
            .build()
}
