package com.soethan.data.repository

import com.soethan.data.model.RateEntity

interface ExchangeLocalDataSource {
    suspend fun saveRate(rates: List<RateEntity>)
    suspend fun getAllRatesFromDb(): List<RateEntity>
    suspend fun deleteAll()
    suspend fun getExchangeRate(id: Long): RateEntity
}