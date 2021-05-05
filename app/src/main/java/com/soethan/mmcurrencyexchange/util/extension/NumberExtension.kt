package com.soethan.mmcurrencyexchange.util.extension

import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

fun String.getDecimalConvertedValue(): Double {
    val format = NumberFormat.getInstance(Locale.getDefault())
    return format.parse(this)?.toDouble() ?: 0.0

}


fun BigDecimal.formatString(): String {
    return DecimalFormat("#,###.##").format(this).toString()
}

fun BigDecimal.setTwoDecimal(): BigDecimal {
    return setScale(2, RoundingMode.HALF_EVEN)
}