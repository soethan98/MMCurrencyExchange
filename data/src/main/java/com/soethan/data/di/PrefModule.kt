package com.soethan.data.di

import android.content.Context
import android.content.SharedPreferences
import com.soethan.data.local.PrefManager
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val PREF_MODULE = module {
    single<SharedPreferences> {
        androidContext().getSharedPreferences(
            androidContext().packageName + "_preferences",
            Context.MODE_PRIVATE
        )
    }
    single { PrefManager(get()) }
}


