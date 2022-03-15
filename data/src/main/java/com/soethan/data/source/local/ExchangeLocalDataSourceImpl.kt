package com.soethan.data.source.local

import com.soethan.data.model.OBankWithRate
import com.soethan.data.model.RateEntity
import com.soethan.data.util.Bank
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

    override suspend fun getExchangeRate(id: Long) =
        exchangeRateDatabase.exchangeRateDao().getExchangeRate(id)

    override suspend fun saveOtherBankRate(rate: OBankWithRate) {
        exchangeRateDatabase.otherBanksExchangeRateDao().insertEntity(rate)
    }

    override fun getOBankRate(bank: Bank): Flow<OBankWithRate> {
        return exchangeRateDatabase.otherBanksExchangeRateDao().getOBankWithRate(bankType = bank)
    }

}