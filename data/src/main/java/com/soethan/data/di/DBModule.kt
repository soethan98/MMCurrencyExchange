package com.soethan.data.di

import androidx.room.Room
import com.soethan.data.source.local.ExchangeRateDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

private const val DATABASE_NAME = "ExchangeRateDatabase"

val DB_MODULE = module {
    single {
        Room.databaseBuilder(androidContext(), ExchangeRateDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration().build()
    }
}



