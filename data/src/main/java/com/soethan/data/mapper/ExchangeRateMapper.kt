package com.soethan.data.mapper

import com.soethan.data.model.RateDataModel
import com.soethan.data.model.CurrencyDataModel
import com.soethan.domain.ExchangeModel
import com.soethan.domain.RateItem

class ExchangeRateMapper: BaseMapper<ExchangeModel, CurrencyDataModel>() {
    override fun map(e: CurrencyDataModel) = with(e) {
        ExchangeModel(
            lastUpdateTime = lastUpdateTime,
            rateList = mapRateList(rateList)
        )
    }


    private fun mapRateList(rates: List<RateDataModel>) =
        rates.map {
            RateItem(
                exchangeRate = it.rate,
                currencyCode = it.currencyCode,
                id = it.id
            )
        }




}


