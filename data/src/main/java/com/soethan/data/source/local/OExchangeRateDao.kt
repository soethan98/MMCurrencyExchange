package com.soethan.data.source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.soethan.data.model.OBankWithRate
import com.soethan.data.util.Bank
import kotlinx.coroutines.flow.Flow

@Dao
interface OExchangeRateDao {

    @Insert
    suspend fun insertAll(rates: List<OBankWithRate>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEntity(rate: OBankWithRate)

    @Query("SELECT * FROM other_bank_with_rate WHERE bankType = :bankType")
    fun getOBankWithRate(bankType: Bank): Flow<OBankWithRate>

    @Query("DELETE  FROM other_bank_with_rate")
    suspend fun delete()
}