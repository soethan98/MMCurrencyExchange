package com.soethan.data.repository

import com.soethan.data.local.ExchangeRateDatabase
import com.soethan.data.model.RateEntity
import kotlinx.coroutines.flow.Flow
import java.math.BigDecimal

class ExchangeLocalDataSourceImpl(private val exchangeRateDatabase: ExchangeRateDatabase) :
    ExchangeLocalDataSource {
    override suspend fun saveRate(rates: List<RateEntity>) {
        exchangeRateDatabase.exchangeRateDao().insertAll(rates)
    }

    override fun getAllRatesFromDb(): Flow<List<RateEntity>> =
        exchangeRateDatabase.exchangeRateDao().getAllExchangeRates()


    override suspend fun deleteAll() {
        exchangeRateDatabase.exchangeRateDao().deleteAll()
    }

    override suspend fun getExchangeRate(id: Long): RateEntity {
        return RateEntity(id = 1, currencyCode = "11", rate = BigDecimal.ONE)
    }

//    override suspend fun getExchangeRate(id: Long) =
//        exchangeRateDatabase.exchangeRateDao().getExchangeRate(id)

}