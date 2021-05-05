package com.soethan.domain.di

import com.soethan.domain.usecase.GetExchangeRateItem
import com.soethan.domain.usecase.GetExchangeRateList
import com.soethan.domain.usecase.IsNightMode
import com.soethan.domain.usecase.ToggleNightMode
import org.koin.dsl.module

val USE_CASE_MODULE = module {
    factory { GetExchangeRateList(get()) }
    factory { IsNightMode(get()) }
    factory { ToggleNightMode(get()) }
    factory { GetExchangeRateItem(get()) }
}