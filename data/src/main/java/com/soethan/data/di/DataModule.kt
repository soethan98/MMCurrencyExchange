package com.soethan.data.di

import com.soethan.data.local.ExchangeRateDatabase
import com.soethan.data.repository.*
import com.soethan.data.util.ConnectivityChecker
import com.soethan.domain.repo.ExchangeRepo
import com.soethan.domain.repo.SettingRepo
import org.koin.core.scope.get
import org.koin.dsl.module

val DATA_MODULE = module {
    single { ConnectivityChecker(get()) }
    single<ExchangeRemoteDataSource> { ExchangeRemoteDataSourceImpl(get()) }
    single<ExchangeRepo> { ExchangeRepoImpl(get(), get(), get(), get(), get(), get()) }
    single<SettingRepo> { SettingRepoImpl(get()) }
    single<ExchangeLocalDataSource> {
        ExchangeLocalDataSourceImpl(get<ExchangeRateDatabase>().exchangeRateDao())
    }
}
