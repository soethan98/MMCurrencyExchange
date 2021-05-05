package com.soethan.data.repository

import com.soethan.data.local.ExchangeRateDatabase
import com.soethan.data.model.RateEntity

class ExchangeLocalDataSourceImpl(private val exchangeRateDatabase: ExchangeRateDatabase) :
    ExchangeLocalDataSource {
    override suspend fun saveRate(rates: List<RateEntity>) {
        exchangeRateDatabase.exchangeRateDao().insertAll(rates)
    }

    override suspend fun getAllRatesFromDb(): List<RateEntity> {
        return exchangeRateDatabase.exchangeRateDao().getAllExchangeRates()
    }


    override suspend fun deleteAll() {
        exchangeRateDatabase.exchangeRateDao().deleteAll()
    }

    override suspend fun getExchangeRate(id: Long) =
        exchangeRateDatabase.exchangeRateDao().getExchangeRate(id)
    
}