package com.soethan.data.source.local

import com.soethan.data.model.OBankWithRate
import com.soethan.data.model.RateEntity
import com.soethan.data.util.Bank
import kotlinx.coroutines.flow.Flow
import java.util.*

interface ExchangeLocalDataSource {
    suspend fun saveRate(rates: List<RateEntity>)
    fun getAllRatesFromDb(): Flow<List<RateEntity>>
    suspend fun deleteAll()
    suspend fun getExchangeRate(id: Long): RateEntity

    suspend fun saveOtherBankRate(rate: OBankWithRate)
    fun getOBankRate(bank: Bank): Flow<OBankWithRate>
}