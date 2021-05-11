package com.soethan.mmcurrencyexchange.di

import com.soethan.mmcurrencyexchange.util.ExchangeCalculator
import org.koin.dsl.module

val CALCULATOR_MODULE = module {
    factory { ExchangeCalculator() }
}