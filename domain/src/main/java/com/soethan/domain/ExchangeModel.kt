package com.soethan.domain

import java.math.BigDecimal

data class RateItem(
    val currencyCode: String,
    val exchangeRate: BigDecimal,
    val id: Long
)

data class ExchangeModel(
    val lastUpdateTime: Long,
    val rateList: List<RateItem>

)