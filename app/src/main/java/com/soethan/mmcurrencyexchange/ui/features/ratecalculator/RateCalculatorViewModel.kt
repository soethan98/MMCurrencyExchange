package com.soethan.mmcurrencyexchange.ui.features.ratecalculator

import androidx.lifecycle.*
import com.soethan.domain.usecase.GetExchangeRateItem
import com.soethan.mmcurrencyexchange.model.RateCalculatorUiModel
import com.soethan.mmcurrencyexchange.ui.base.BaseViewModel
import com.soethan.mmcurrencyexchange.util.ExchangeCalculator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@FlowPreview
@ExperimentalCoroutinesApi
class RateCalculatorViewModel constructor(
    private val getExchangeRateItem: GetExchangeRateItem,
    private val rateCalculatorMapper: RateCalculatorMapper,
    private val exchangeRateCalculator: ExchangeCalculator
) : BaseViewModel() {


    private lateinit var rateCalculatorUiModel: RateCalculatorUiModel

    private val _rateCalculatorLiveData = MutableLiveData<RateCalculatorUiModel>()
    val rateCaLiveData: LiveData<RateCalculatorUiModel>
        get() = _rateCalculatorLiveData

    private val _throwableLiveData = MutableLiveData<Throwable>()
    val throwableLiveData: LiveData<Throwable>
        get() = _throwableLiveData

    val amount = MutableStateFlow(INITIAL_AMOUNT)

    override fun handleError(t: Throwable) {
        _throwableLiveData.postValue(t)
    }

    init {
        viewModelScope.launch {
            amount.filter { this@RateCalculatorViewModel::rateCalculatorUiModel.isInitialized }
                .map { return@map exchangeRateCalculator.calculate(it) }
                .catch { e -> _throwableLiveData.postValue(e) }
                .flowOn(Dispatchers.Main)
                .collect()
        }
    }

    fun getExchangeRateFromDb(id: Long) {
        viewModelScope.launch(exceptionHandler) {
            val rateItem = getExchangeRateItem.execute(id)
            rateCalculatorUiModel = rateCalculatorMapper.map(rateItem)
            exchangeRateCalculator.setRate(rateCalculatorUiModel)
            _rateCalculatorLiveData.postValue(rateCalculatorUiModel)
        }
    }

    fun reverseCurrencyCode() {
        viewModelScope.launch {
            exchangeRateCalculator.reverseCalculationState()
        }
    }

    fun getCalculationState() {
        exchangeRateCalculator.rateCalculationStateFlow.onEach {
            _rateCalculatorLiveData.postValue(it)
        }.launchIn(viewModelScope)
    }


    companion object {
        const val INITIAL_AMOUNT = "1.00"
    }


}