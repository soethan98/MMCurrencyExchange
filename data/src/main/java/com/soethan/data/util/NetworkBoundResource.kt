package com.soethan.data.util

import com.soethan.domain.util.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: suspend () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: suspend (ResultType) -> Boolean = { true },
    crossinline onFetchSuccess: () -> Unit = {},
    crossinline onFetchFailed: (Throwable) -> Unit = {}
) = flow {
    val data = query().first()
    val flow = if (shouldFetch(data)) {
        query().map { Result.Loading }
        try {
            saveFetchResult(fetch())
            onFetchSuccess()
            query().map {
                Result.Success(it)
            }

        } catch (t: Throwable) {
            onFetchFailed(t)
            query().map { Result.Error(t) }
        }
    } else {
        query().map { Result.Success(it) }
    }

    emitAll(flow)
    
}