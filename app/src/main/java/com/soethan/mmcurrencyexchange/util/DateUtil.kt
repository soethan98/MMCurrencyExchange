package com.soethan.mmcurrencyexchange.util

import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

object DateFormatter {
    private val formatter = SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.US)


    fun format(time: Long): String {
        Timber.i("Time %s",time)
        val cal = Calendar.getInstance()
        cal.timeInMillis = time * 1000L
        return formatter.format(cal.time).toString()
    }

}