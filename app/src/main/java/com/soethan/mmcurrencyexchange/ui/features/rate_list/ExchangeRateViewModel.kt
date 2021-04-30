 package com.soethan.mmcurrencyexchange.ui.features.rate_list

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soethan.domain.usecase.GetExchangeRate
import com.soethan.domain.usecase.IsNightMode
import com.soethan.domain.usecase.ToggleNightMode
import com.soethan.mmcurrencyexchange.mapper.ExchangeRateUiModelMapper
import com.soethan.mmcurrencyexchange.model.ExchangeUiModel
import com.soethan.mmcurrencyexchange.util.Resource
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch


class ExchangeRateViewModel(
    private val getExchangeRate: GetExchangeRate,
    private val exchangeRateUiModelMapper: ExchangeRateUiModelMapper) : ViewModel() {

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _exchangeRateLiveData.postValue(Resource.Error(throwable))
    }


    private var _exchangeRateLiveData = MutableLiveData<Resource<ExchangeUiModel>>()
    val exchangeRateLiveData get() = _exchangeRateLiveData



    fun getExchangeRate(isForceRefresh: Boolean = false) {
        _exchangeRateLiveData.postValue(Resource.Loading)
        viewModelScope.launch(exceptionHandler) {
            val exchangeModel = getExchangeRate.execute(isForceRefresh)
            _exchangeRateLiveData.postValue(
                Resource.Content(
                    exchangeRateUiModelMapper.map(exchangeModel)
                )
            )
        }
    }



}