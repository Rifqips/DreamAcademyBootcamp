package com.rifqipadisiliwangi.dreamacademy_bootcamp.feature.home

import com.rifqipadisiliwangi.dreamacademy_bootcamp.data.network.ResponseStatus
import com.rifqipadisiliwangi.dreamacademy_bootcamp.data.network.api.MakeupApi
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainPresenter(
    private val view: MainActivity,
    private val api: MakeupApi,
    uiContext: CoroutineContext = Dispatchers.Main
): MainContract.Presenter {
    private val supervisorJob: Job = SupervisorJob()
    private val scope = CoroutineScope(supervisorJob + uiContext)

    override fun onAttach() {
        getMakeUp()
        api.getError {
            scope.launch {
                when (it) {
                    is ResponseStatus.Failed -> view.onError(it.message)
                    else -> {}
                }
            }
        }
    }

    override fun onDetach() {
        scope.cancel()
    }

    fun getMakeUp(){
        view.onLoading()
        api.getProducts {
            scope.launch {
                when (it) {
                    is ResponseStatus.Success -> view.onSuccessGetMakeup(it.data)
                    is ResponseStatus.Failed -> view.onError(it.message)
                }
                view.onFinishedLoading()
            }
        }
    }
}