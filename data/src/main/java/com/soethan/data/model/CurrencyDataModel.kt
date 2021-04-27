package com.soethan.data.model

import com.soethan.data.model.RateDataModel

data class CurrencyDataModel(
    val lastUpdateTime: Long,
    val rateList: List<RateDataModel>
)