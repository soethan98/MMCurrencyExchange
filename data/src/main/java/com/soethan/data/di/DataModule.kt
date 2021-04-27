package com.soethan.data.di

import android.content.Context
import android.content.SharedPreferences
import com.soethan.data.repository.*
import com.soethan.data.util.ConnectivityChecker
import com.soethan.domain.ExchangeRepo
import org.koin.dsl.module

val DATA_MODULE = module {
    single { ConnectivityChecker(get()) }
    single<ExchangeRemoteDataSource> { ExchangeRemoteDataSourceImpl(get()) }
    single<ExchangeRepo> { ExchangeRepoImpl(get(), get(), get(), get(),get()) }
    single<ExchangeLocalDataSource> { ExchangeLocalDataSourceImpl(get()) }
}
