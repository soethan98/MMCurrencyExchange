package com.soethan.mmcurrencyexchange.util.extension

import android.app.Activity
import android.app.ActivityOptions
import android.content.res.Configuration
import androidx.fragment.app.Fragment

fun Fragment.restartActivity() {
    val currentActivity = requireActivity()
    val intent = currentActivity.intent
    currentActivity.finish()
    val animationBundle =
        ActivityOptions.makeCustomAnimation(
            requireContext(),
            android.R.anim.fade_in, android.R.anim.fade_out
        ).toBundle()
    startActivity(intent, animationBundle)
}
fun Activity.isNightMode():Boolean{
    return when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
        Configuration.UI_MODE_NIGHT_YES -> true
        else -> false
    }
}