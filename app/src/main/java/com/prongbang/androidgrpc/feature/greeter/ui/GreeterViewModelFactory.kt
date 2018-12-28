package com.prongbang.androidgrpc.feature.greeter.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.prongbang.androidgrpc.feature.greeter.domain.SayHelloUseCase

class GreeterViewModelFactory(
    private val sayHelloUseCase: SayHelloUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GreeterViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GreeterViewModel(sayHelloUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}