package com.rifqipadisiliwangi.dreamacademy_bootcamp.feature.home

import com.rifqipadisiliwangi.dreamacademy_bootcamp.data.model.MakeUpItem


interface MainContract {
    interface Presenter {
        fun onAttach()
        fun onDetach()
    }

    interface View {
        fun onLoading()
        fun onFinishedLoading()
        fun onError(message: String)
        fun onSuccessGetMakeup(makeup: List<MakeUpItem>) {}
    }
}