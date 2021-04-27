package com.soethan.mmcurrencyexchange.di

import com.soethan.mmcurrencyexchange.ui.features.rate_list.ExchangeRateListFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.androidx.fragment.dsl.fragment
import org.koin.dsl.module

@ExperimentalCoroutinesApi
@FlowPreview
val FRAGMENT_MODULE = module {
    fragment { ExchangeRateListFragment(get()) }
}