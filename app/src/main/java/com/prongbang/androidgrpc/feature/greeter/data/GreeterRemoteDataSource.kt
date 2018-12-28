package com.prongbang.androidgrpc.feature.greeter.data

import com.prongbang.androidgrpc.feature.greeter.grpc.GrpcService
import io.prongbang.grpc.GreeterGrpc
import io.prongbang.grpc.HelloRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit

class GreeterRemoteDataSource constructor(private val grpcService: GrpcService) {

    suspend fun sayHello(message: String) = withContext(Dispatchers.IO) {

        return@withContext try {

            val channel = grpcService.createManagedChannel()

            val stub = GreeterGrpc.newBlockingStub(channel)

            val request = HelloRequest.newBuilder().setName(message).build()

            val reply = stub.sayHello(request)

            channel?.shutdown()?.awaitTermination(1, TimeUnit.SECONDS)

            reply.message
        } catch (e: Exception) {
            String.format("Failed... : %s", e.message)
        }
    }

}