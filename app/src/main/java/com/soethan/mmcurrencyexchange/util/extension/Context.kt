package com.soethan.mmcurrencyexchange.util.extension

import android.content.Context

fun Context.getApplicationVersionName(): String{
    return packageManager.getPackageInfo(packageName, 0).versionName
}