package com.soethan.data.di

import com.soethan.data.repository.*
import com.soethan.data.source.local.ExchangeLocalDataSource
import com.soethan.data.source.local.ExchangeLocalDataSourceImpl
import com.soethan.data.source.network.CBBankScrapper
import com.soethan.data.source.network.ExchangeRemoteDataSource
import com.soethan.data.source.network.ExchangeRemoteDataSourceImpl
import com.soethan.data.source.network.OtherBankScrapper
import com.soethan.data.util.ConnectivityChecker
import com.soethan.domain.repo.ExchangeRepo
import com.soethan.domain.repo.SettingRepo
import org.koin.dsl.module

val DATA_MODULE = module {
    single { ConnectivityChecker(get()) }
    single<ExchangeRemoteDataSource> { ExchangeRemoteDataSourceImpl(get()) }
    single<ExchangeRepo> { ExchangeRepoImpl(get(), get(), get(), get(), get(), get(), get()) }
    single<SettingRepo> { SettingRepoImpl(get()) }
    single<ExchangeLocalDataSource> { ExchangeLocalDataSourceImpl(get()) }
    single<OtherBankScrapper> { CBBankScrapper() }
}
