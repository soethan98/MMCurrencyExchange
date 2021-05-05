 package com.soethan.mmcurrencyexchange.ui.features.rate_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soethan.domain.usecase.GetExchangeRateList
import com.soethan.mmcurrencyexchange.mapper.ExchangeRateUiModelMapper
import com.soethan.mmcurrencyexchange.model.ExchangeUiModel
import com.soethan.mmcurrencyexchange.util.BaseViewModel
import com.soethan.mmcurrencyexchange.util.Resource
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch


class ExchangeRateViewModel(
    private val getExchangeRateList: GetExchangeRateList,
    private val exchangeRateUiModelMapper: ExchangeRateUiModelMapper) : BaseViewModel() {

    override fun handleError(t: Throwable) {
        _exchangeRateLiveData.postValue(Resource.Error(throwable = t))
    }


    private var _exchangeRateLiveData = MutableLiveData<Resource<ExchangeUiModel>>()
    val exchangeRateLiveData get() = _exchangeRateLiveData



    fun getExchangeRate(isForceRefresh: Boolean = false) {
        _exchangeRateLiveData.postValue(Resource.Loading)
        viewModelScope.launch(exceptionHandler) {
            val exchangeModel = getExchangeRateList.execute(isForceRefresh)
            _exchangeRateLiveData.postValue(
                Resource.Content(
                    exchangeRateUiModelMapper.map(exchangeModel)
                )
            )
        }
    }



}