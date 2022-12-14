package com.rifqipadisiliwangi.dreamacademy_bootcamp.feature.login

import com.rifqipadisiliwangi.dreamacademy_bootcamp.data.model.UserPagination

interface LoginView {
    fun onLoading()
    fun onFinishedLoading()
    fun onError(code: Int, message: String)
    fun onErrorPassword(visible: Boolean, message: String)
    fun resetPasswordError()
    fun onSuccessLogin(username: String, password: String)
    fun onSuccessGetUser(user: UserPagination)
    fun onSuccessRegister()
}