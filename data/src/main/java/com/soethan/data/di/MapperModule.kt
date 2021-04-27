package com.soethan.data.di

import com.soethan.data.mapper.ExchangeRateMapper
import org.koin.dsl.module

val DATA_MAPPER_MODULE = module {
    factory { ExchangeRateMapper() }
}