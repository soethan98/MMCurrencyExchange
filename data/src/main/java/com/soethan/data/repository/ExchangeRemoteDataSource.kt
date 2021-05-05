package com.soethan.data.repository

import com.soethan.data.network.model.CurrencyApiModel

interface ExchangeRemoteDataSource {
    suspend fun getExchangeFromNetwork(): CurrencyApiModel
}