package com.soethan.data.source.network.model

import com.soethan.data.util.Bank

class OCurrencyModel(
    val lastUpdateDate: String,
    val bankName: Bank,
    val currencyRates: List<OCurrencyRate>
)