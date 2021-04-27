package com.soethan.data.model

import com.google.gson.annotations.SerializedName

data class CurrencyApiModel(
    @SerializedName("timestamp")
    val date: Long,
    @SerializedName("rates")
    val rates: Map<String, String>?
) {
    fun getRates(): List<RateDataModel> {
        return rates?.map {
            RateDataModel(
                currencyCode = it.key,
                rate = it.value
            )
        } ?: listOf()
    }
}