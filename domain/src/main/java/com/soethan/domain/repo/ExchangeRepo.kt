package com.soethan.domain.repo

import com.soethan.domain.ExchangeModel
import com.soethan.domain.RateItem


interface ExchangeRepo {
    suspend fun getCurrencies(forceRefresh: Boolean): ExchangeModel

    suspend fun getExchangeRate(id: Long): RateItem

}