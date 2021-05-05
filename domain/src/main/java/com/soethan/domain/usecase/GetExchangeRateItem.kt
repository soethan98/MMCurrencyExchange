package com.soethan.domain.usecase

import com.soethan.domain.RateItem
import com.soethan.domain.repo.ExchangeRepo

class GetExchangeRateItem constructor(private val exchangeRepo: ExchangeRepo) : BaseUseCase() {
    suspend fun execute(id: Long): RateItem {
        return exchangeRepo.getExchangeRate(id)
    }
}