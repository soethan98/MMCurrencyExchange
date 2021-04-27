package com.soethan.mmcurrencyexchange.util

import com.soethan.mmcurrencyexchange.R

fun getFlagIcon(currencyCode: String): Int = when (currencyCode) {
    "USD" -> R.drawable.ic_united_states
    "EUR" -> R.drawable.ic_european_union
    "SGD" -> R.drawable.ic_singapore
    "GBP" -> R.drawable.ic_england
    "CHF" -> R.drawable.ic_switzerland
    "JPY" -> R.drawable.ic_japan
    "AUD" -> R.drawable.ic_austraila
    "BDT" -> R.drawable.ic_bangladesh
    "BND" -> R.drawable.ic_brunei
    "KHR" -> R.drawable.ic_cambodia
    "CAD" -> R.drawable.ic_canada
    "CNY" -> R.drawable.ic_china
    "HKD" -> R.drawable.ic_hong_kong
    "INR" -> R.drawable.ic_india
    "IDR" -> R.drawable.ic_indonesia
    "KRW" -> R.drawable.ic_korea
    "LAK" -> R.drawable.ic_laos
    "MYR" -> R.drawable.ic_malasya
    "NZD" -> R.drawable.ic_new_zealand
    "PKR" -> R.drawable.ic_pakistan
    "PHP" -> R.drawable.ic_philphines
    "LKR" -> R.drawable.ic_srilanka
    "THB" -> R.drawable.ic_thailand
    "VND" -> R.drawable.ic_vietnam
    "BRL" -> R.drawable.ic_brazil
    "CZK" -> R.drawable.ic_czech_republic
    "DKK" -> R.drawable.ic_denmark
    "EGP" -> R.drawable.ic_egypt
    "ILS" -> R.drawable.ic_israel
    "KES" -> R.drawable.ic_kenya
    "KWD" -> R.drawable.ic_kwait
    "NPR" -> R.drawable.ic_nepal
    "NOK" -> R.drawable.ic_norway
    "RUB" -> R.drawable.ic_russia
    "SAR" -> R.drawable.ic_saudi
    "RSD" -> R.drawable.ic_serbia
    "ZAR" -> R.drawable.ic_south_africa
    "SEK" -> R.drawable.ic_sweden


    else -> R.drawable.ic_austraila
}