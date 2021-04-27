package com.soethan.data.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

class ConnectivityChecker  constructor(private val context: Context) {

    fun isNetworkAvailable(): Boolean {
        val service = Context.CONNECTIVITY_SERVICE
        val manager = context.getSystemService(service) as ConnectivityManager?
        val network = manager?.activeNetworkInfo
        return network != null
    }

}

