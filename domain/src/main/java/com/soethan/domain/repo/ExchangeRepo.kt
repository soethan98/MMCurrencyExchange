package com.soethan.domain.repo

import com.soethan.domain.ExchangeModel
import com.soethan.domain.RateItem
import kotlinx.coroutines.flow.Flow
import com.soethan.domain.util.Result


interface ExchangeRepo {

    suspend fun getCurrenciesFlow(
        forceRefresh: Boolean,
        onFetchFailed: (Throwable) -> Unit,
        onFetchSuccess: () -> Unit
    ): Flow<Result<ExchangeModel>>

    suspend fun getExchangeRate(id: Long): RateItem

    //suspend fun getCbExchangeRate():Flow<Result<ExchangeModel>>

}