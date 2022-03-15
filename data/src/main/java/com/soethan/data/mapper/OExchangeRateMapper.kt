package com.soethan.data.mapper

import com.soethan.data.model.OBankEntity
import com.soethan.data.model.OBankWithRate
import com.soethan.data.source.network.model.OCurrencyModel

class OExchangeRateMapper : BaseMapper<OBankWithRate, OCurrencyModel>() {

    override fun map(e: OCurrencyModel): OBankWithRate {
        OBankWithRate(
            oBankEntity = OBankEntity(
                bankType = e.bankName,
                updateTime = e.lastUpdateDate.toLong(),

            )
        )
        TODO("Not yet implemented")
    }

}