package com.soethan.data.repository

import com.soethan.data.model.RateEntity
import kotlinx.coroutines.flow.Flow
import java.util.*

interface ExchangeLocalDataSource {
    suspend fun saveRate(rates: List<RateEntity>)
    fun getAllRatesFromDb(): Flow<List<RateEntity>>
    suspend fun deleteAll()
    suspend fun getExchangeRate(id: Long): RateEntity
}