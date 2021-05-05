package com.soethan.mmcurrencyexchange.ui.features.ratecalculator

import androidx.lifecycle.*
import com.soethan.domain.usecase.GetExchangeRateItem
import com.soethan.mmcurrencyexchange.model.RateCalculatorUiModel
import com.soethan.mmcurrencyexchange.util.BaseViewModel
import com.soethan.mmcurrencyexchange.util.ExchangeCalculator
import com.soethan.mmcurrencyexchange.util.extension.getDecimalConvertedValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception
import java.math.BigDecimal

@FlowPreview
@ExperimentalCoroutinesApi
class RateCalculatorViewModel constructor(
    private val getExchangeRateItem: GetExchangeRateItem,
    private val rateCalculatorMapper: RateCalculatorMapper
) : BaseViewModel() {


    private lateinit var rateCalculatorUiModel: RateCalculatorUiModel
    private var exchangeRateCalculator = ExchangeCalculator()

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
                .collect {
                    _rateCalculatorLiveData.postValue(it)
                }
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

    companion object {
        const val INITIAL_AMOUNT = "1.00"
    }

//    private fun calculateValue(amount: String?): RateCalculatorUiModel {
//        val rate = rateCalculatorUiModel.rate
//        val calculatedAmount = BigDecimal(amount ?: INITIAL_AMOUNT).multiply(rate)
//        return rateCalculatorUiModel.copy(calculatedAmount = calculatedAmount.toString())
//    }

//    private fun calculateValue(amount: String): RateCalculatorUiModel {
//        val rate = rateCalculatorUiModel.rate
//
////        if (amount.isEmpty()) {
////            return rateCalculatorUiModel.copy(
////                calculatedAmount = rate.convertReadableFormat()
////            )
////        }
//        return rateCalculatorUiModel.copy(
//            calculatedAmount = (rate * amount).convertReadableFormat()
//        )
//    }

//    fun postInitialValue() {
//        _rateCalculatorLiveData.postValue(rateCalculatorUiModel)
//    }


//    fun setUpModel(code: String, rate: String) {
//        rateCalculatorUiModel = RateCalculatorUiModel(
//            code = code,
//            rate = rate.getDecimalConvertedValue(),
//            calculatedAmount = rate,
//        )
//        postInitialValue()
//    }



}