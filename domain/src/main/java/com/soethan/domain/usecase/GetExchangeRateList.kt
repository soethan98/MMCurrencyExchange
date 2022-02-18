package com.soethan.domain.usecase

import com.soethan.domain.ExchangeModel
import com.soethan.domain.util.Result
import com.soethan.domain.repo.ExchangeRepo
import kotlinx.coroutines.flow.Flow

class GetExchangeRateList constructor(private val exchangeRepo: ExchangeRepo) : BaseUseCase() {
    suspend fun execute(
        isForceRefresh: Boolean, onFetchSuccess: () -> Unit,
        onFetchFailed: (Throwable) -> Unit
    ): Flow<Result<ExchangeModel>> {
        return exchangeRepo.getCurrenciesFlow(
            isForceRefresh, onFetchFailed = onFetchFailed,
            onFetchSuccess = onFetchSuccess
        )
    }
}