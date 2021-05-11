package com.soethan.mmcurrencyexchange.di

import com.soethan.mmcurrencyexchange.ui.features.rate_list.ExchangeRateViewModel
import com.soethan.mmcurrencyexchange.ui.features.ratecalculator.RateCalculatorViewModel
import com.soethan.mmcurrencyexchange.ui.features.settings.SettingsViewModel
import com.soethan.mmcurrencyexchange.ui.main.MainViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@ExperimentalCoroutinesApi
@FlowPreview
val VIEW_MODEL_MODULE = module{
    viewModel { MainViewModel(get()) }
    viewModel { SettingsViewModel(get()) }
    viewModel{ ExchangeRateViewModel(get(), get()) }
    viewModel{ RateCalculatorViewModel(get(),get(),get()) }
}