package com.soethan.mmcurrencyexchange.model

import java.util.*


data class ExchangeRateUiModel(
    override val id: Long,
    val rate: String,
    val code: String,
    val countryName: String = "United States"
) : BaseUiModel

data class ExchangeUiModel(
    val lastUpdateTime: String,
    val exchangeRateUiModel: List<ExchangeRateUiModel>
)


data class CurrencyUiModel(
    val aa: String,
    val currencies: Map<String, String>
) {
    fun getValue(key: String): String? {
        return currencies[key]
    }
}