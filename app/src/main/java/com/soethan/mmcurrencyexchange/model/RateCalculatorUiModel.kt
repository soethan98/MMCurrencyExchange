package com.soethan.mmcurrencyexchange.model

import java.math.BigDecimal


data class RateCalculatorUiModel(
    val code: String,
    val rate: BigDecimal,
    val calculatedAmount: String,
    val rateCalculationState: RateCalculationState = RateCalculationState.TO_KYAT,
)