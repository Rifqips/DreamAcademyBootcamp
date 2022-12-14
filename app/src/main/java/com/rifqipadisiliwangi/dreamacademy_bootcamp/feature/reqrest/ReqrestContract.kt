package com.rifqipadisiliwangi.dreamacademy_bootcamp.feature.reqrest

import com.rifqipadisiliwangi.dreamacademy_bootcamp.data.model.User

interface ReqrestContract {

    interface Presenter{
        fun onAttach()
        fun onDetach()
    }

    interface View{
        fun onLoading()
        fun onFinishedLoading()
        fun onError(message: String)
        fun onSuccessGetUser(user: List<User>){}
        fun onSuccessAddUser(){}
    }
}