package com.prongbang.androidgrpc.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.prongbang.androidgrpc.result.Result

/**
 * Executes business logic synchronously or asynchronously using a [Coroutines].
 */
abstract class UseCase<in Params, R> {

    /** Executes the use case asynchronously and places the [Results] in a MutableLiveData
     *
     * @param parameters the input parameters to run the use case with
     * @param result the MutableLiveData where the result is posted to
     *
     */
    suspend operator fun invoke(parameters: Params, result: MutableLiveData<Result<R>>) {
        try {
            withContext(Dispatchers.Default) {
                result.postValue(Result.Loading)
                try {
                    execute(parameters).let { useCaseResult ->
                        result.postValue(Result.Success(useCaseResult))
                    }
                } catch (e: Exception) {
                    result.postValue(Result.Error(e))
                }
            }
        } catch (e: Exception) {
            result.postValue(Result.Error(e))
        }
    }

    /** Executes the use case asynchronously and returns a [Results] in a new LiveData object.
     *
     * @return an observable [LiveData] with a [Results].
     *
     * @param parameters the input parameters to run the use case with
     */
    suspend operator fun invoke(parameters: Params): LiveData<Result<R>> {
        val liveCallback: MutableLiveData<Result<R>> = MutableLiveData()
        this(parameters, liveCallback)
        return liveCallback
    }

    /**
     * Override this to set the code to be executed.
     */
    @Throws(RuntimeException::class)
    protected abstract suspend fun execute(parameters: Params): R
}

suspend operator fun <R> UseCase<Unit, R>.invoke(): LiveData<Result<R>> = this(Unit)
suspend operator fun <R> UseCase<Unit, R>.invoke(result: MutableLiveData<Result<R>>) = this(Unit, result)