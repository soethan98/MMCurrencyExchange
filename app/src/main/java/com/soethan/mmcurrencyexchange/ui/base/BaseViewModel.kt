package com.soethan.mmcurrencyexchange.ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler

abstract class BaseViewModel : ViewModel() {

    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        handleError(throwable)
    }

    abstract fun handleError(t: Throwable)
}