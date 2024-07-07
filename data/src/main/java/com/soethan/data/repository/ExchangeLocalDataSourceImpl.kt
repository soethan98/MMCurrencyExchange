package com.soethan.data.repository

import com.soethan.data.local.ExchangeRateDao
import com.soethan.data.local.ExchangeRateDatabase
import com.soethan.data.model.RateEntity

class ExchangeLocalDataSourceImpl(private val exchangeRateDao: ExchangeRateDao) :
    ExchangeLocalDataSource {
    override suspend fun saveRate(rates: List<RateEntity>) {
        exchangeRateDao.insertAll(rates)
    }

    override suspend fun getAllRatesFromDb(): List<RateEntity> {
        return exchangeRateDao.getAllExchangeRates()
    }


    override suspend fun deleteAll() {
        exchangeRateDao.deleteAll()
    }

    override suspend fun getExchangeRate(id: Long) =
        exchangeRateDao.getExchangeRate(id)
    
}