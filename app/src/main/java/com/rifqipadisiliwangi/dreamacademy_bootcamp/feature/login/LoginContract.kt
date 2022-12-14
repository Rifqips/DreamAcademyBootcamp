package com.rifqipadisiliwangi.dreamacademy_bootcamp.feature.login

interface LoginContract {
    fun onLoading()
    fun onFinishedLoading()
    fun onError(code:Int, message: String)
    fun onSuccessLogin()
}