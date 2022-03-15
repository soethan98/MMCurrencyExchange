package com.soethan.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.soethan.data.model.RateEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface ExchangeRateDao {

    @Query("SELECT * FROM rate_table")
    fun getAllExchangeRates(): Flow<List<RateEntity>>

    @Query("SELECT * FROM rate_table WHERE id=:id")
    suspend fun getExchangeRate(id: Long): RateEntity

    @Insert
    suspend fun insertAll(rates: List<RateEntity>)
    

    @Query("DELETE FROM rate_table")
    suspend fun deleteAll()
}