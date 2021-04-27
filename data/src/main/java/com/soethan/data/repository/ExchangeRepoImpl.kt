package com.soethan.data.repository

import com.soethan.data.local.PrefManager
import com.soethan.data.mapper.ExchangeRateMapper
import com.soethan.data.model.RateDataModel
import com.soethan.data.model.CurrencyDataModel
import com.soethan.data.util.ConnectivityChecker
import com.soethan.domain.ExchangeModel
import com.soethan.domain.ExchangeRepo

class ExchangeRepoImpl(
    private val exchangeRemoteDataSource: ExchangeRemoteDataSource,
    private val exchangeLocalDataSource: ExchangeLocalDataSource,
    private val exchangeRateMapper: ExchangeRateMapper,
    private val connectivityChecker: ConnectivityChecker,
    private val prefManager: PrefManager
) : ExchangeRepo {


    override suspend fun getCurrencies(forceRefresh: Boolean): ExchangeModel {
        if (connectivityChecker.isNetworkAvailable() && checkValidTimeToFetchFromNetwork(
                forceRefresh
            )
        ) {
            val currency = exchangeRemoteDataSource.getExchangeFromNetwork()
            prefManager.save(PrefManager.SERVER_SYNC_TIME, System.currentTimeMillis())
            prefManager.save(PrefManager.SERVER_UPDATE_TIME, currency.date)
            synCurrency(currency.getRates())
        }
        val serverUpdateTime = prefManager.getValue(PrefManager.SERVER_UPDATE_TIME)
        return exchangeRateMapper.map(
            CurrencyDataModel(
                lastUpdateTime = serverUpdateTime,
                rateList = exchangeLocalDataSource.getAllRatesFromDb()
            )
        )
    }

    suspend fun synCurrency(updatedRates: List<RateDataModel>) {
        exchangeLocalDataSource.deleteAll()
        exchangeLocalDataSource.saveRate(updatedRates)
    }


    private fun checkValidTimeToFetchFromNetwork(refresh: Boolean): Boolean {
        val lastUpdateTime = prefManager.getValue(PrefManager.SERVER_SYNC_TIME)
        val diff = (System.currentTimeMillis() - lastUpdateTime) / (1000 * 60 * 60 * 24)
        if (!refresh && lastUpdateTime != 0L && diff < 1) {
            return false
        }

        return true


    }
}