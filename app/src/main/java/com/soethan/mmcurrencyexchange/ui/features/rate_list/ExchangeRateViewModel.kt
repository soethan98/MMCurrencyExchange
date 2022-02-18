package com.soethan.mmcurrencyexchange.ui.features.rate_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.soethan.domain.usecase.GetExchangeRateList
import com.soethan.domain.util.*
import com.soethan.mmcurrencyexchange.mapper.ExchangeRateUiModelMapper
import com.soethan.mmcurrencyexchange.model.ExchangeRateUiModel
import com.soethan.mmcurrencyexchange.model.ExchangeUiModel
import com.soethan.mmcurrencyexchange.ui.base.BaseViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber

sealed class Event {
    data class ShowErrorMessage(val error: Throwable) : Event()
}

enum class Refresh {
    FORCE, NORMAL
}


class ExchangeRateViewModel(
    private val getExchangeRateList: GetExchangeRateList,
    private val exchangeRateUiModelMapper: ExchangeRateUiModelMapper
) : BaseViewModel() {

    private val eventChannel = Channel<Event>()
    val events = eventChannel.receiveAsFlow()

    private val refreshTriggerChannel = Channel<Refresh>()
    private val refreshTrigger = refreshTriggerChannel.receiveAsFlow()

    override fun handleError(t: Throwable) {
        //  _exchangeRateLiveData.postValue(.Error(throwable = t))
    }

    val getExchangeRate = refreshTrigger.flatMapLatest { refresh ->
        getExchangeRateList.execute(isForceRefresh = refresh == Refresh.FORCE,
            onFetchSuccess = {},
            onFetchFailed = { t ->
                viewModelScope.launch { eventChannel.send(Event.ShowErrorMessage(t)) }
            })
    }.map { result ->
        result.mapResult {
            exchangeRateUiModelMapper.map(it)
        }
    }.stateIn(viewModelScope, SharingStarted.Lazily, null)


    fun onStart() {
        if (getExchangeRate.value != LoadingResult) {
            viewModelScope.launch {
                refreshTriggerChannel.send(Refresh.NORMAL)
            }
        }
    }

    fun onManualRefresh() {
        if (getExchangeRate.value != LoadingResult) {
            viewModelScope.launch {
                refreshTriggerChannel.send(Refresh.NORMAL)
            }
        }
    }


//    private var _exchangeRateLiveData = MutableLiveData<Result<ExchangeUiModel>>()
//    val exchangeRateLiveData get() = _exchangeRateLiveData


//    fun getExchangeRate(isForceRefresh: Boolean = false) {
//
//        //getExchangeRateList.execute(isForceRefresh).
////        _exchangeRateLiveData.postValue(Lce.Loading)
////        viewModelScope.launch(exceptionHandler) {
////            val exchangeModel = getExchangeRateList.execute(isForceRefresh)
////            _exchangeRateLiveData.postValue(
////                Lce.Content(
////                    exchangeRateUiModelMapper.map(exchangeModel)
////                )
////            )
////        }
//    }


}