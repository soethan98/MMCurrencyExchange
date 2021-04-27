package com.soethan.mmcurrencyexchange.di

import com.soethan.mmcurrencyexchange.App
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val CONTEXT_MODULE = module {
    single { androidApplication() as App }

}