package com.soethan.data.repository

import com.soethan.data.model.CurrencyApiModel

interface ExchangeRemoteDataSource {
    suspend fun getExchangeFromNetwork(): CurrencyApiModel
}