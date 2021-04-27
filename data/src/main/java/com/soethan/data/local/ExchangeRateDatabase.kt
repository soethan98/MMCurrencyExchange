package com.soethan.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.soethan.data.model.RateDataModel

@Database(
    entities = [RateDataModel::class],
    version = 1,
    exportSchema = false
)
abstract class ExchangeRateDatabase : RoomDatabase() {
    abstract fun exchangeRateDao(): ExchangeRateDao
}