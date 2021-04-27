package com.soethan.data.repository

import com.soethan.data.model.RateDataModel

interface ExchangeLocalDataSource {
    suspend fun saveRate(rates: List<RateDataModel>)
    suspend fun getAllRatesFromDb(): List<RateDataModel>
    suspend fun deleteAll()
}