package com.soethan.mmcurrencyexchange.mapper

import android.content.Context
import com.google.gson.Gson
import com.soethan.data.mapper.BaseMapper
import com.soethan.domain.ExchangeModel
import com.soethan.mmcurrencyexchange.model.CurrencyUiModel
import com.soethan.mmcurrencyexchange.model.ExchangeRateUiModel
import com.soethan.mmcurrencyexchange.model.ExchangeUiModel
import com.soethan.mmcurrencyexchange.util.DateFormatter
import com.soethan.mmcurrencyexchange.util.extension.formatString
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import kotlin.jvm.Throws

class ExchangeRateUiModelMapper constructor(
    private val context: Context
) : BaseMapper<ExchangeUiModel, ExchangeModel>() {


    override fun map(e: ExchangeModel): ExchangeUiModel {
        val currencyUiModel = convertFromAsset()
        return with(e) {
            ExchangeUiModel(lastUpdateTime = DateFormatter.format(lastUpdateTime),
                exchangeRateUiModel = rateList.map { rateItem ->
                    ExchangeRateUiModel(
                        code = rateItem.currencyCode,
                        rate = rateItem.exchangeRate.formatString(),
                        id = rateItem.id,
                        countryName = currencyUiModel?.getValue(rateItem.currencyCode)
                            ?: "Unknown value"
                    )
                }
            )

        }
    }


    private fun convertFromAsset(): CurrencyUiModel? {
        val gson = Gson()
        return gson.fromJson(getCurrencyJson(), CurrencyUiModel::class.java)
    }


    @Throws(IOException::class)
    private fun getCurrencyJson(fileName: String = "currency.json"): String {
        val inputStream = context.assets.open(fileName)
        val bufferedReader =
            BufferedReader(InputStreamReader(inputStream))

        val total = StringBuilder()
        var line: String?
        while (bufferedReader.readLine().also { line = it } != null) {
            total.append(line).append('\n')
        }
        return total.toString()
    }

}


