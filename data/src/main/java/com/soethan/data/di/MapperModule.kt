package com.soethan.data.di

import com.soethan.data.mapper.ExchangeRateDbMapper
import com.soethan.data.mapper.ExchangeRateDomainMapper
import org.koin.dsl.module

val DATA_MAPPER_MODULE = module {
    factory { ExchangeRateDomainMapper() }
    factory { ExchangeRateDbMapper() }
}