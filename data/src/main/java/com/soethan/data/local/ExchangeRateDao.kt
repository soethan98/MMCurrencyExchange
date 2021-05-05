package com.soethan.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.soethan.data.model.RateEntity

@Dao
interface ExchangeRateDao {

    @Query("SELECT * FROM rate_table")
    suspend fun getAllExchangeRates(): List<RateEntity>

    @Query("SELECT * FROM rate_table WHERE id=:id")
    suspend fun getExchangeRate(id: Long): RateEntity

    @Insert
    suspend fun insertAll(rates: List<RateEntity>): List<Long>

    @Query("DELETE FROM rate_table")
    suspend fun deleteAll()
}