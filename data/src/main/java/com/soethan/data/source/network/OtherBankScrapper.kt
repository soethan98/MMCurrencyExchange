package com.soethan.data.source.network

import com.soethan.data.source.network.model.OCurrencyModel

interface OtherBankScrapper {

    suspend fun scrapCurrencyList(): OCurrencyModel
}