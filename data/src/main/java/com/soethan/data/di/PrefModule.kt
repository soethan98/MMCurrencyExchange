package com.soethan.data.di

import com.soethan.data.source.local.PrefStore
import com.soethan.data.source.local.PrefStoreImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val PREF_MODULE = module {
    single<PrefStore> { PrefStoreImpl(androidContext()) }
}


