package com.soethan.data.source.network

import com.soethan.data.source.network.model.CurrencyApiModel
import com.soethan.data.source.network.service.ExchangeRateService

class ExchangeRemoteDataSourceImpl constructor(private val exchangeRateService: ExchangeRateService) :
    ExchangeRemoteDataSource {

    override suspend fun getExchangeFromNetwork(): CurrencyApiModel {
        return exchangeRateService.getCurrencyExchange()
    }


}