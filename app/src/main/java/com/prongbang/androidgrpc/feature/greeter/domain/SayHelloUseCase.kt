package com.prongbang.androidgrpc.feature.greeter.domain

import com.prongbang.androidgrpc.domain.UseCase
import com.prongbang.androidgrpc.feature.greeter.data.GreeterRepository

class SayHelloUseCase constructor(
    private val greeterRepository: GreeterRepository
): UseCase<String, String>() {

    override suspend fun execute(parameters: String): String {

        return greeterRepository.sayHello(parameters)
    }

}