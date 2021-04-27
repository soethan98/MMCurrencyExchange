package com.soethan.mmcurrencyexchange.util

sealed class Resource<out T : Any> {
    object Loading : Resource<Nothing>()
    data class Error(val throwable: Throwable) : Resource<Nothing>()
    data class Content<out T : Any>(val content: T) : Resource<T>()
}
