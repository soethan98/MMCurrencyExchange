package com.soethan.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.soethan.data.local.converter.BigDecimalTypeConverter
import com.soethan.data.model.RateEntity

@Database(
    entities = [RateEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(BigDecimalTypeConverter::class)
abstract class ExchangeRateDatabase : RoomDatabase() {
    abstract fun exchangeRateDao(): ExchangeRateDao
}