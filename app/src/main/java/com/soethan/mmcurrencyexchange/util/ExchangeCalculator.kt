package com.soethan.mmcurrencyexchange.util

import com.soethan.mmcurrencyexchange.model.RateCalculatorUiModel
import com.soethan.mmcurrencyexchange.util.extension.formatString
import com.soethan.mmcurrencyexchange.util.extension.setTwoDecimal
import timber.log.Timber
import java.math.BigDecimal
import java.math.RoundingMode

class ExchangeCalculator() {

    private lateinit var selectedCurrencyRate: RateCalculatorUiModel

    fun setRate(rateCalculatorUiModel: RateCalculatorUiModel) {
        this.selectedCurrencyRate = rateCalculatorUiModel
    }

    fun calculate(enterValue: String): RateCalculatorUiModel {
        val value = if (enterValue.isEmpty()) {
            "1.0"
        } else enterValue
        val calculatedRate = BigDecimal(value).multiply(selectedCurrencyRate.rate).setTwoDecimal()
        return selectedCurrencyRate.copy(calculatedAmount = calculatedRate.formatString())
    }
}


