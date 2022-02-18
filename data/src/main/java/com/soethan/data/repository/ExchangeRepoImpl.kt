package com.soethan.data.repository

import com.soethan.data.local.PrefStore
import com.soethan.data.mapper.ExchangeRateDbMapper
import com.soethan.data.mapper.ExchangeRateDomainMapper
import com.soethan.data.model.CurrencyDataModel
import com.soethan.data.network.model.CurrencyApiItem
import com.soethan.data.util.ConnectivityChecker
import com.soethan.data.util.networkBoundResource
import com.soethan.domain.ExchangeModel
import com.soethan.domain.RateItem
import com.soethan.domain.repo.ExchangeRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import com.soethan.domain.util.Result
import timber.log.Timber

@ExperimentalCoroutinesApi
class ExchangeRepoImpl(
    private val exchangeRemoteDataSource: ExchangeRemoteDataSource,
    private val exchangeLocalDataSource: ExchangeLocalDataSource,
    private val exchangeRateDomainMapper: ExchangeRateDomainMapper,
    private val exchangeRateDbMapper: ExchangeRateDbMapper,
    private val connectivityChecker: ConnectivityChecker,
    private val prefStore: PrefStore
) : ExchangeRepo {


    override suspend fun getCurrenciesFlow(
        forceRefresh: Boolean, onFetchFailed: (Throwable) -> Unit,
        onFetchSuccess: () -> Unit
    ): Flow<Result<ExchangeModel>> {

        return networkBoundResource(query = {
            val serverUpdateTime = prefStore.getServerUpdateTime().first()
            val dbResult = exchangeLocalDataSource.getAllRatesFromDb()
            dbResult.map {
                exchangeRateDomainMapper.map(
                    CurrencyDataModel(
                        lastUpdateTime = serverUpdateTime,
                        rateList = it
                    )
                )
            }
        }, fetch = {
            exchangeRemoteDataSource.getExchangeFromNetwork()

        }, saveFetchResult = {
            withContext(Dispatchers.IO) {
                prefStore.saveSyncTime(System.currentTimeMillis())
                prefStore.saveServerUpdateTime(it.date)
                synCurrency(it.getRates())
            }
        }, shouldFetch = { item ->
            connectivityChecker.isNetworkAvailable() && checkValidTimeToFetchFromNetwork(
                forceRefresh
            )
        }, onFetchFailed = onFetchFailed,
            onFetchSuccess = onFetchSuccess
        )


    }

    private suspend fun synCurrency(updatedRates: List<CurrencyApiItem>) {
        exchangeLocalDataSource.deleteAll()
        exchangeLocalDataSource.saveRate(exchangeRateDbMapper.map(updatedRates))
    }

    override suspend fun getExchangeRate(id: Long): RateItem {
        return exchangeRateDomainMapper.mapRateItem(exchangeLocalDataSource.getExchangeRate(id))
    }


    private suspend fun checkValidTimeToFetchFromNetwork(refresh: Boolean): Boolean {
        val lastUpdateTime = prefStore.getServerSyncTime().first()
        val diff = (System.currentTimeMillis() - lastUpdateTime) / (1000 * 60 * 60 * 24)
        if (!refresh && lastUpdateTime != 0L && diff < 1) {
            return false
        }

        return true
    }
}