package com.soethan.domain.util

sealed class Result<out R> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val throwable: Throwable) : Result<Nothing>()
    object Loading : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$throwable]"
            is Loading -> "Loading"
        }
    }
}

fun <T> Result<T>.getDataOrThrow(): T =
    when (this) {
        is Result.Loading -> throw Exception("Data not found")
        is Result.Success -> data
        is Result.Error -> throw  throwable
    }


val <T> Result<T>.data: T
    get() = (this as Result.Success).data

typealias ErrorResult = Result.Error
typealias LoadingResult = Result.Loading
typealias SuccessResult<T> = Result.Success<T>

val Result<*>.succeeded
    get() = this is SuccessResult && data != null


fun <T> Result<T>.successOr(fallback: T): T {
    return (this as? Result.Success<T>)?.data ?: fallback
}

fun <R, T> Result<R>.mapResult(op: (R) -> T): Result<T> {
     return when (this) {
        is LoadingResult -> Result.Loading
        is ErrorResult -> Result.Error(throwable)
        is SuccessResult -> Result.Success(op(data))
    }


}

//this is used with the 'when' block to specify all cases
val <T> T.exhaustive: T
    get() = this