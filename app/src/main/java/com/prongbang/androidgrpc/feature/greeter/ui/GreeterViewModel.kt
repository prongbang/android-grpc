package com.prongbang.androidgrpc.feature.greeter.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.prongbang.androidgrpc.feature.greeter.domain.SayHelloUseCase
import com.prongbang.androidgrpc.result.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class GreeterViewModel constructor(private val sayHelloUseCase: SayHelloUseCase) : ViewModel() {

    private var sayHelloJob: Job? = null

    fun sayHello(message: String): LiveData<Result<String>> {

        val liveDataMerger = MediatorLiveData<Result<String>>()

        sayHelloJob?.cancel()

        sayHelloJob = GlobalScope.launch(Dispatchers.Main) {

            liveDataMerger.addSource(sayHelloUseCase.invoke(message)) { value ->
                liveDataMerger.setValue(value)
            }

        }

        return liveDataMerger
    }

    override fun onCleared() {
        super.onCleared()
        sayHelloJob?.cancel()
    }

}