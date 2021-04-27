package com.soethan.mmcurrencyexchange.di

import com.soethan.data.di.*
import com.soethan.domain.di.USE_CASE_MODULE

val APP_DI_MODULES = listOf(

    NET_MODULE,
    DB_MODULE,
    DATA_MODULE,
    DATA_MAPPER_MODULE,
    CONTEXT_MODULE,
    VIEW_MODEL_MODULE,
    FRAGMENT_MODULE,
    UI_MAPPER_MODULE,
    USE_CASE_MODULE,
    PREF_MODULE
)