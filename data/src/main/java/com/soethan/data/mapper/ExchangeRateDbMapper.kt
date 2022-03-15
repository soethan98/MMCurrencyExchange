package com.soethan.data.mapper

import com.soethan.data.model.RateEntity
import com.soethan.data.source.network.model.CurrencyApiItem
import java.math.BigDecimal

class ExchangeRateDbMapper : BaseMapper<List<RateEntity>, List<CurrencyApiItem>>() {
    override fun map(e: List<CurrencyApiItem>): List<RateEntity> {
        with(e) {
            return map {
                RateEntity(
                    currencyCode = it.currencyCode,
                    rate = convertBigDecimal(rate = it.currencyRate)
                )
            }
        }
    }


    private fun validateInputNumber(rate: String) {
        rate.toDoubleOrNull()
            ?: throw InvalidateInputNumberException("Invalidate Input number")
    }

    private fun convertBigDecimal(rate: String): BigDecimal {
        val commaRemovedRate = rate.replace(",", "")
        validateInputNumber(commaRemovedRate)
        return BigDecimal(commaRemovedRate)
    }
}


class InvalidateInputNumberException(msg: String?) : Exception(msg)

