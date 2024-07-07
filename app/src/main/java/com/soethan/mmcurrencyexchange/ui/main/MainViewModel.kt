package com.soethan.mmcurrencyexchange.ui.main

import androidx.lifecycle.*
import com.soethan.domain.usecase.IsNightMode
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch

class MainViewModel constructor(private val isNightMode: IsNightMode) : ViewModel() {
    fun isDarkThemeEnabled(): LiveData<Boolean> {
        return isNightMode.execute().asLiveData(context = viewModelScope.coroutineContext)
    }

}