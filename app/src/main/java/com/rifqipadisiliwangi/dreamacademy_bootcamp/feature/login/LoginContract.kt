package com.rifqipadisiliwangi.dreamacademy_bootcamp.feature.login

interface LoginContract {
    fun onLoading()
    fun onFinishedLoading()
    fun onError(message: String)
    fun onSuccessLogin()
}