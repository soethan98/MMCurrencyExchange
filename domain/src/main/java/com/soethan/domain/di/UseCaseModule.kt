package com.soethan.domain.di

import com.soethan.domain.usecase.GetExchangeRate
import org.koin.dsl.module

val USE_CASE_MODULE = module {
    factory { GetExchangeRate(get()) }
}