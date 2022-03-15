package com.soethan.data.source.network

import com.soethan.data.util.Bank
import com.soethan.data.source.network.model.OCurrencyModel
import com.soethan.data.source.network.model.OCurrencyRate
import com.soethan.data.util.JsoupUtil
import java.io.IOException

class CBBankScrapper : OtherBankScrapper {

    override suspend fun scrapCurrencyList(): OCurrencyModel {
        val document = JsoupUtil.getPageData("https://www.cbbank.com.mm/en") ?: throw IOException()
        val parentClass = document.select("div.currency-exchange")
        val updateDate =
            parentClass.select("div.update-date").text().removePrefix("Updated on").trim()
        val currencies = mutableListOf<OCurrencyRate>()
        parentClass.select("div.currency-info").forEachIndexed { index, currencyDetail ->
            if (index == 0) return@forEachIndexed
            val currencyType = currencyDetail.select("div.currency-type").text()
            val currencyBuy = currencyDetail.select("div.currency-buy").text()
            val currencySell = currencyDetail.select("div.currency-sell").text()
            currencies += OCurrencyRate(
                country = currencyType,
                buy = currencyBuy,
                sell = currencySell
            )

        }
        return OCurrencyModel(
            currencyRates = currencies,
            bankName = Bank.CB,
            lastUpdateDate = updateDate
        )

    }
}