package com.soethan.data.repository

import com.soethan.data.model.CurrencyApiModel
import com.soethan.data.service.ExchangeRateService

class ExchangeRemoteDataSourceImpl constructor(private val exchangeRateService: ExchangeRateService) :
    ExchangeRemoteDataSource {
    override suspend fun getExchangeFromNetwork(): CurrencyApiModel {
        return exchangeRateService.getCurrencyExchange()
    }


}