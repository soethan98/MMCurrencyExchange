package com.soethan.data.repository

import android.util.Log
import com.soethan.data.local.ExchangeRateDatabase
import com.soethan.data.model.RateDataModel

class ExchangeLocalDataSourceImpl(private val exchangeRateDatabase: ExchangeRateDatabase) :
    ExchangeLocalDataSource {
    override suspend fun saveRate(rates: List<RateDataModel>) {
        exchangeRateDatabase.exchangeRateDao().insertAll(rates)
    }

    override suspend fun getAllRatesFromDb(): List<RateDataModel> {
        return exchangeRateDatabase.exchangeRateDao().getAllExchangeRates()
    }


    override suspend fun deleteAll() {
        exchangeRateDatabase.exchangeRateDao().deleteAll()
    }
}