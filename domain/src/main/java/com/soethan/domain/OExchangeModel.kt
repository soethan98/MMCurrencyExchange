package com.soethan.domain

import java.math.BigDecimal

data class ORateItem(
    val currencyCode: String,
    val buyRate: BigDecimal,
    val sellRate: BigDecimal,
    val id: Long
)

data class OExchangeModel(
    val lastUpdateTime: Long,
    val rateList: List<ORateItem>,
    val bank: String

)