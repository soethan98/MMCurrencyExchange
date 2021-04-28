package com.soethan.domain.usecase

import com.soethan.domain.ExchangeModel
import com.soethan.domain.repo.ExchangeRepo

class GetExchangeRate constructor(private val exchangeRepo: ExchangeRepo) : BaseUseCase() {
    suspend fun execute(isForceRefresh:Boolean): ExchangeModel {
        return exchangeRepo.getCurrencies(isForceRefresh)
    }
}