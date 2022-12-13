package com.rifqipadisiliwangi.dreamacademy_bootcamp.feature.login

class LoginPresenter {
    private var view: LoginContract? = null

    fun onAttach(view: LoginContract) {
        this.view = view
    }

    fun onDetach() {
        this.view = null
    }

    fun validateCredential(userName: String, password: String) {
        view?.onLoading()
        val isPasswordValid = password.contains("[a-z]".toRegex())
                && password.contains("[A-Z]".toRegex())
                && password.contains("[0-9]".toRegex())
                && password.length >= 8

        val isUsernameValid = userName.length > 5

        if (isPasswordValid && isUsernameValid) {
            view?.onSuccessLogin()
        } else if (!isUsernameValid) {
            view?.onError("invalid username")
        } else {
            view?.onError("invalid password")
        }

        view?.onFinishedLoading()

    }
}