package com.soethan.data.source.network

import com.soethan.data.source.network.model.CurrencyApiModel

interface ExchangeRemoteDataSource {
    suspend fun getExchangeFromNetwork(): CurrencyApiModel
}