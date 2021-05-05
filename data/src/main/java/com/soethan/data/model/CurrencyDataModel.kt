package com.soethan.data.model

data class CurrencyDataModel(
    val lastUpdateTime: Long,
    val rateList: List<RateEntity>
)