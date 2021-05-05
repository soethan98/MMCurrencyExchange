package com.soethan.data.mapper

import com.soethan.data.model.RateEntity
import com.soethan.data.model.CurrencyDataModel
import com.soethan.domain.ExchangeModel
import com.soethan.domain.RateItem

class ExchangeRateDomainMapper : BaseMapper<ExchangeModel, CurrencyDataModel>() {
    override fun map(e: CurrencyDataModel) = with(e) {
        ExchangeModel(
            lastUpdateTime = lastUpdateTime,
            rateList = mapRateList(rateList)
        )
    }


    private fun mapRateList(rates: List<RateEntity>) =
        rates.map {
            mapRateItem(it)
        }

    fun mapRateItem(rateEntity: RateEntity):RateItem{
        with(rateEntity) {
            return RateItem(
                exchangeRate = rate,
                currencyCode = currencyCode,
                id = id
            )
        }
    }


}


