package com.soethan.data.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.soethan.data.local.ExchangeRateDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

private const val DATABASE_NAME = "ExchangeRateDatabase"

val DB_MODULE = module {
    single<ExchangeRateDatabase> {
        Room.databaseBuilder(androidContext(), ExchangeRateDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration().build()
    }
}



