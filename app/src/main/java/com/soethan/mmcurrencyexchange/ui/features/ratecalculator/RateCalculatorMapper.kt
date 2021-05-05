package com.soethan.mmcurrencyexchange.ui.features.ratecalculator

import com.soethan.data.mapper.BaseMapper
import com.soethan.domain.RateItem
import com.soethan.mmcurrencyexchange.model.RateCalculatorUiModel
import com.soethan.mmcurrencyexchange.util.extension.formatString

class RateCalculatorMapper : BaseMapper<RateCalculatorUiModel, RateItem>() {

    override fun map(e: RateItem): RateCalculatorUiModel {
        with(e) {
            return RateCalculatorUiModel(
                code = this.currencyCode,
                rate = this.exchangeRate,
                calculatedAmount = this.exchangeRate.formatString()
            )
        }
    }
}