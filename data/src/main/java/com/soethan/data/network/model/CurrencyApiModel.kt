package com.soethan.data.network.model

import com.google.gson.annotations.SerializedName

data class CurrencyApiModel(
    @SerializedName("timestamp")
    val date: Long,
    @SerializedName("rates")
    val rates: Map<String, String>?
) {
    fun getRates(): List<CurrencyApiItem> {
        return rates?.map {
            CurrencyApiItem(
                currencyCode = it.key,
                currencyRate = it.value
            )
        } ?: listOf()
    }
}

