package com.soethan.domain.repo

import com.soethan.domain.ExchangeModel


interface ExchangeRepo {
    suspend fun getCurrencies(forceRefresh: Boolean): ExchangeModel

}