package com.soethan.mmcurrencyexchange.di

import com.soethan.mmcurrencyexchange.mapper.ExchangeRateUiModelMapper
import com.soethan.mmcurrencyexchange.ui.features.ratecalculator.RateCalculatorMapper
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val UI_MAPPER_MODULE = module {
    factory { ExchangeRateUiModelMapper(androidContext()) }
    factory { RateCalculatorMapper() }
}