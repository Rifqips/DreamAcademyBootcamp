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
        val isPasswordValid =
            password.contains("[a-z]".toRegex())
                && password.contains("[A-Z]".toRegex())
                && password.contains("[0-9]".toRegex())
                && password.length >= 8

        val isUsernameValid = userName.length > 5

        if (isPasswordValid && isUsernameValid) { view?.onSuccessLogin() }

        if (!isUsernameValid) { view?.onError(0,"invalid username") }
        if (!isPasswordValid) { view?.onError(1,"invalid password") }
        if (!isUsernameValid && !isPasswordValid) { view?.onError(2,"invalid username & password")}

        view?.onFinishedLoading()

    }
}