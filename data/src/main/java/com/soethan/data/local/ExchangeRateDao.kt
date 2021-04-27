package com.soethan.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.soethan.data.model.RateDataModel
import retrofit2.http.DELETE

@Dao
interface ExchangeRateDao {

    @Query("SELECT * FROM rate_table")
    suspend fun getAllExchangeRates(): List<RateDataModel>

    @Insert
    suspend fun insertAll(rates: List<RateDataModel>): List<Long>

    @Query("DELETE FROM rate_table")
    suspend fun deleteAll()
}