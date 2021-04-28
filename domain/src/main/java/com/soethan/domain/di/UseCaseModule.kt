package com.soethan.domain.di

import com.soethan.domain.usecase.GetExchangeRate
import com.soethan.domain.usecase.IsNightMode
import com.soethan.domain.usecase.ToggleNightMode
import org.koin.dsl.module

val USE_CASE_MODULE = module {
    factory { GetExchangeRate(get()) }
    factory { IsNightMode(get()) }
    factory { ToggleNightMode(get()) }
}