package com.soethan.mmcurrencyexchange.ui.features.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.soethan.domain.usecase.IsNightMode
import com.soethan.domain.usecase.ToggleNightMode
import kotlinx.coroutines.launch

class SettingsViewModel constructor(
    private val toggleNightMode: ToggleNightMode) : ViewModel() {


    fun toggleNightMode() {
        viewModelScope.launch { toggleNightMode.execute() }
    }


}