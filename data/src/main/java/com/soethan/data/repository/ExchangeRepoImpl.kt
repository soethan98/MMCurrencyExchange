package com.soethan.data.repository

import com.soethan.data.local.PrefStore
import com.soethan.data.mapper.ExchangeRateDbMapper
import com.soethan.data.mapper.ExchangeRateDomainMapper
import com.soethan.data.model.CurrencyDataModel
import com.soethan.data.network.model.CurrencyApiItem
import com.soethan.data.util.ConnectivityChecker
import com.soethan.domain.ExchangeModel
import com.soethan.domain.RateItem
import com.soethan.domain.repo.ExchangeRepo
import kotlinx.coroutines.flow.first

class ExchangeRepoImpl(
    private val exchangeRemoteDataSource: ExchangeRemoteDataSource,
    private val exchangeLocalDataSource: ExchangeLocalDataSource,
    private val exchangeRateDomainMapper: ExchangeRateDomainMapper,
    private val exchangeRateDbMapper: ExchangeRateDbMapper,
    private val connectivityChecker: ConnectivityChecker,
    private val prefStore: PrefStore
) : ExchangeRepo {

    override suspend fun getCurrencies(forceRefresh: Boolean): ExchangeModel {
        if (connectivityChecker.isNetworkAvailable() && checkValidTimeToFetchFromNetwork(forceRefresh)) {
            val currency = exchangeRemoteDataSource.getExchangeFromNetwork()
            prefStore.saveSyncTime(System.currentTimeMillis())
            prefStore.saveServerUpdateTime(currency.date)
            synCurrency(currency.getRates())
        }
        val serverUpdateTime = prefStore.getServerUpdateTime().first()
        return exchangeRateDomainMapper.map(
            CurrencyDataModel(
                lastUpdateTime = serverUpdateTime,
                rateList = exchangeLocalDataSource.getAllRatesFromDb()
            )
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