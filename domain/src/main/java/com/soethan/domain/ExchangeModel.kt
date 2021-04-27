package com.soethan.domain

data class RateItem(
    val currencyCode: String,
    val exchangeRate: String,
    val id: Long
)

data class ExchangeModel(
    val lastUpdateTime: Long,
    val rateList: List<RateItem>

)