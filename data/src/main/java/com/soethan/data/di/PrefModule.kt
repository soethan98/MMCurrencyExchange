package com.soethan.data.di

import android.content.Context
import android.content.SharedPreferences
import com.soethan.data.local.PrefStore
import com.soethan.data.local.PrefStoreImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val PREF_MODULE = module {
    single<PrefStore> { PrefStoreImpl(androidContext()) }
}


