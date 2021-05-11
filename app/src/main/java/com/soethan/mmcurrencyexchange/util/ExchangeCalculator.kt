package com.soethan.mmcurrencyexchange.util

import com.soethan.mmcurrencyexchange.model.RateCalculationState
import com.soethan.mmcurrencyexchange.model.RateCalculatorUiModel
import com.soethan.mmcurrencyexchange.util.extension.formatString
import com.soethan.mmcurrencyexchange.util.extension.setTwoDecimal
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.*
import java.math.BigDecimal
import java.math.RoundingMode

@ExperimentalCoroutinesApi
@FlowPreview
class ExchangeCalculator() {

    private lateinit var selectedCurrencyRate: RateCalculatorUiModel


    private var _rateCalculationChannel = ConflatedBroadcastChannel<RateCalculatorUiModel>()
    val rateCalculationStateFlow: Flow<RateCalculatorUiModel>
        get() = _rateCalculationChannel.asFlow()


    fun setRate(rateCalculatorUiModel: RateCalculatorUiModel) {
        this.selectedCurrencyRate = rateCalculatorUiModel
    }

    suspend fun calculate(enterValue: String) {
        val value = if (enterValue.isEmpty()) {
            "1.0"
        } else enterValue
        val calculatedRate = when (selectedCurrencyRate.rateCalculationState) {
            RateCalculationState.TO_KYAT -> {
                BigDecimal(value).multiply(selectedCurrencyRate.rate).setTwoDecimal()
            }
            else -> BigDecimal(value).divide(
                selectedCurrencyRate.rate,
                15,
                RoundingMode.HALF_UP
            )
        }

        broadcastChannel(selectedCurrencyRate.copy(calculatedAmount = calculatedRate.formatString()))
    }


    private suspend fun broadcastChannel(selectedCurrencyUiModel: RateCalculatorUiModel) {
        if (!_rateCalculationChannel.isClosedForSend) {
            _rateCalculationChannel.send(selectedCurrencyUiModel)
        }
    }


    suspend fun reverseCalculationState() {
        val reversedState = when (selectedCurrencyRate.rateCalculationState) {
            RateCalculationState.FROM_KYAT -> RateCalculationState.TO_KYAT
            RateCalculationState.TO_KYAT -> RateCalculationState.FROM_KYAT
        }
        selectedCurrencyRate = selectedCurrencyRate.copy(rateCalculationState = reversedState)
        setRate(selectedCurrencyRate)
        calculate("")
    }
}


