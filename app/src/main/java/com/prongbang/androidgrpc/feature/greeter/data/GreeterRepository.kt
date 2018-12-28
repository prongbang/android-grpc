package com.prongbang.androidgrpc.feature.greeter.data

class GreeterRepository constructor(private val greeterRemoteDataSource: GreeterRemoteDataSource) {

    suspend fun sayHello(message: String): String {

        return greeterRemoteDataSource.sayHello(message)
    }
}