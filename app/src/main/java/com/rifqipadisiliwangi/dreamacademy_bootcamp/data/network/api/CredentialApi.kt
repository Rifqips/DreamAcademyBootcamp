package com.rifqipadisiliwangi.dreamacademy_bootcamp.data.network.api

import com.rifqipadisiliwangi.dreamacademy_bootcamp.data.model.LoginRegisterModel
import com.rifqipadisiliwangi.dreamacademy_bootcamp.data.model.RegisterResult
import com.rifqipadisiliwangi.dreamacademy_bootcamp.data.network.ResponseStatus
import com.rifqipadisiliwangi.dreamacademy_bootcamp.data.network.deserializeJson
import com.rifqipadisiliwangi.dreamacademy_bootcamp.data.network.mapFailedResponse
import com.rifqipadisiliwangi.dreamacademy_bootcamp.data.network.serialized
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class CredentialApi {
    fun registerUser(email: String, password: String): Flow<ResponseStatus<RegisterResult>> = flow {
        val model = LoginRegisterModel(email, password)
        try {
            val result = NetworkClient
                .makeCallApi("/register?delay=2", NetworkClient.METHOD.POST, model.serialized())
                .execute()
            val response = if (result.isSuccessful) {
                val data: RegisterResult = deserializeJson<RegisterResult>(result.body?.string() ?: "") ?: RegisterResult()
                ResponseStatus.Success(data)
            } else {
                mapFailedResponse(result)
            }
            emit(response)
            result.body?.close()
        } catch (e: IOException) {
            emit(ResponseStatus.Failed(-1, e.message.toString(), e))
        }
    }
}