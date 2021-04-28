package com.soethan.mmcurrencyexchange.di

import com.soethan.mmcurrencyexchange.ui.features.rate_list.ExchangeRateViewModel
import com.soethan.mmcurrencyexchange.ui.features.settings.SettingsViewModel
import com.soethan.mmcurrencyexchange.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val VIEW_MODEL_MODULE = module {
    viewModel { MainViewModel(get()) }
    viewModel { SettingsViewModel(get()) }
    viewModel { ExchangeRateViewModel(get(), get()) }
}