package com.prongbang.androidgrpc.feature.greeter.grpc

import io.grpc.ManagedChannelBuilder
import java.util.concurrent.Executors

class GrpcService(private val host: String, private val port: Int) {

    fun createManagedChannel() = ManagedChannelBuilder.forAddress(host, port)
        .executor(Executors.newSingleThreadExecutor())
        .usePlaintext()
        .build()

}