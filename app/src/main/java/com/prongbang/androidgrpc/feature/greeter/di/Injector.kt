package com.prongbang.androidgrpc.feature.greeter.di

import com.prongbang.androidgrpc.feature.greeter.data.GreeterRemoteDataSource
import com.prongbang.androidgrpc.feature.greeter.data.GreeterRepository
import com.prongbang.androidgrpc.feature.greeter.domain.SayHelloUseCase
import com.prongbang.androidgrpc.feature.greeter.grpc.GrpcService
import com.prongbang.androidgrpc.feature.greeter.ui.GreeterViewModelFactory

object Injector {

    // Provide GrpcService
    fun provideGrpcService() = GrpcService(
        "IP SERVER",
        50051
    )

    // Provide DataSource
    fun provideGreeterRemoteDataSource(grpcService: GrpcService) = GreeterRemoteDataSource(grpcService)

    // Provide Repository
    fun provideGreeterRepository(greeterRemoteDataSource: GreeterRemoteDataSource) = GreeterRepository(
        greeterRemoteDataSource
    )

    // Provide UseCase
    fun provideSayHelloUseCase(greeterRepository: GreeterRepository) = SayHelloUseCase(
        greeterRepository
    )

    // Provide ViewModelFactory
    fun provideGreeterViewModelFactory() = GreeterViewModelFactory(
        provideSayHelloUseCase(
            provideGreeterRepository(
                provideGreeterRemoteDataSource(
                    provideGrpcService()
                )
            )
        )
    )
}