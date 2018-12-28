# Android + gRPC

### Blog 

[https://prongbang.github.io/android/grpc/2018/12/28/android-grpc.html](https://prongbang.github.io/android/grpc/2018/12/28/android-grpc.html)


### Change IP server in `Injector.kt`

```kotlin
object Injector {

    // Provide GrpcService
    fun provideGrpcService() = GrpcService(
        "IP SERVER",
        50051
    )
}
```