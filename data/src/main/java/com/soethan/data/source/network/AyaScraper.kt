package com.soethan.data.source.network

import com.soethan.data.source.network.model.OCurrencyModel
import com.soethan.data.util.JsoupUtil
import java.io.IOException

class AyaScraper : OtherBankScrapper {

    override suspend fun scrapCurrencyList(): OCurrencyModel {
        val document =
            JsoupUtil.getPageData("https://www.ayabank.com/en_US/") ?: throw IOException()
        val parentClass = document.select("table.tablepress-id-104")
        throw NotImplementedError()


    }
}