package com.rifqipadisiliwangi.dreamacademy_bootcamp.data.network.api

import com.rifqipadisiliwangi.dreamacademy_bootcamp.data.model.MakeUpItem
import com.rifqipadisiliwangi.dreamacademy_bootcamp.data.network.ResponseStatus
import com.rifqipadisiliwangi.dreamacademy_bootcamp.data.network.deserializeJson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Request
import okhttp3.Response
import org.json.JSONArray
import java.io.IOException

class MakeupApi {
    fun getProducts(onResponse: (ResponseStatus<List<MakeUpItem>>) -> Unit) {
        val request = Request.Builder()
            .url("${NetworkClient.baseUrl}${NetworkClient.productEndpoint}")
            .build()

        NetworkClient.client
            .newCall(request)
            .enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    onResponse.invoke(ResponseStatus.Failed(1,""))
                }

                override fun onResponse(call: Call, response: Response) {
                    if (response.isSuccessful) {
                        val result = mutableListOf<MakeUpItem>()
                        val jsonArray = JSONArray(response.body?.string())
                        for (i in 0 until jsonArray.length()) {
                            val jsonObject = deserializeJson<MakeUpItem>(jsonArray.getString(i))
                            jsonObject?.let { result.add(it) }
                        }

                        onResponse.invoke(ResponseStatus.Success(result))
                    }
                    response.body?.close()
                }
            })
    }

    fun getError(onResponse: (ResponseStatus<Nothing>) -> Unit) {
        NetworkClient
//            .client
//            .newCall(NetworkClient.requestBuilder("/unknown/23"))
//            .enqueue(object: Callback {
//                override fun onFailure(call: Call, e: IOException) {
//                    onResponse.invoke(ResponseStatus.Failed(-1, e.message.toString(), e))
//                }
//
//                override fun onResponse(call: Call, response: Response) {
//                    onResponse.invoke(ResponseStatus.Failed(-1, response.message))
//                    response.body?.close()
//                }
//            })
    }

}