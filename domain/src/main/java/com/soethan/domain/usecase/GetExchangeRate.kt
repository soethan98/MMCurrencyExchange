package com.soethan.domain.usecase

import com.soethan.domain.ExchangeModel
import com.soethan.domain.ExchangeRepo
import com.sun.xml.internal.fastinfoset.algorithm.BooleanEncodingAlgorithm

class GetExchangeRate constructor(private val exchangeRepo: ExchangeRepo) : BaseUseCase() {
    suspend fun execute(isForceRefresh:Boolean): ExchangeModel {
        return exchangeRepo.getCurrencies(isForceRefresh)
    }
}