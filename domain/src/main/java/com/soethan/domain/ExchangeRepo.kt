package com.soethan.domain


interface ExchangeRepo {
    suspend fun getCurrencies(forceRefresh:Boolean): ExchangeModel
}