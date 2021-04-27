package com.soethan.mmcurrencyexchange

import android.app.Application
import com.soethan.data.di.NET_MODULE
import com.soethan.mmcurrencyexchange.di.APP_DI_MODULES
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

    }

    private fun initKoin() {
        startKoin {
            androidContext(this@App)
            fragmentFactory()
            modules(APP_DI_MODULES)
        }
    }
}