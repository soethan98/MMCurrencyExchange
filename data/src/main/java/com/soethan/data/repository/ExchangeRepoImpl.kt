package com.soethan.data.repository

import com.soethan.data.local.PrefStore
import com.soethan.data.mapper.ExchangeRateMapper
import com.soethan.data.model.RateDataModel
import com.soethan.data.model.CurrencyDataModel
import com.soethan.data.util.ConnectivityChecker
import com.soethan.domain.ExchangeModel
import com.soethan.domain.repo.ExchangeRepo
import kotlinx.coroutines.flow.first

class ExchangeRepoImpl(
    private val exchangeRemoteDataSource: ExchangeRemoteDataSource,
    private val exchangeLocalDataSource: ExchangeLocalDataSource,
    private val exchangeRateMapper: ExchangeRateMapper,
    private val connectivityChecker: ConnectivityChecker,
    private val prefStore: PrefStore) : ExchangeRepo {


    override suspend fun getCurrencies(forceRefresh: Boolean): ExchangeModel {
        if (connectivityChecker.isNetworkAvailable() && checkValidTimeToFetchFromNetwork(
                forceRefresh
            )
        ) {
            val currency = exchangeRemoteDataSource.getExchangeFromNetwork()
            prefStore.saveSyncTime(System.currentTimeMillis())
            prefStore.saveServerUpdateTime(currency.date)
            synCurrency(currency.getRates())
        }
        val serverUpdateTime =prefStore.getServerUpdateTime().first()
        return exchangeRateMapper.map(
            CurrencyDataModel(
                lastUpdateTime = serverUpdateTime,
                rateList = exchangeLocalDataSource.getAllRatesFromDb()
            )
        )
    }

   private suspend fun synCurrency(updatedRates: List<RateDataModel>) {
        exchangeLocalDataSource.deleteAll()
        exchangeLocalDataSource.saveRate(updatedRates)
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