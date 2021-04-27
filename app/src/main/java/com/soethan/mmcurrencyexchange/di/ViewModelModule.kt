package com.soethan.mmcurrencyexchange.di

import com.soethan.mmcurrencyexchange.ui.features.rate_list.ExchangeRateViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val VIEW_MODEL_MODULE = module {
    viewModel {
        ExchangeRateViewModel(
            get(),
            get()
        )
    }
}