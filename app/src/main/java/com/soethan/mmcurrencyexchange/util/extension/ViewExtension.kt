package com.soethan.mmcurrencyexchange.util.extension

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.res.Configuration
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.soethan.mmcurrencyexchange.R
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.runBlocking

fun View.show(): View {
    if (visibility != View.VISIBLE) {
        visibility = View.VISIBLE
    }
    return this
}

fun View.hide(): View {
    if (visibility != View.GONE) {
        visibility = View.GONE
    }
    return this
}

fun View.isVisible(): Boolean {
    if (visibility == View.VISIBLE) {
        return true
    }
    return false
}

fun View.invisible(): View {
    if (visibility != View.GONE) {
        visibility = View.INVISIBLE
    }
    return this
}

fun Context.toast(text: String? = "Something went wrong", duration: Int = Toast.LENGTH_LONG) {
    return this.let {
        Toast.makeText(it, text, duration).show()

    }
}

fun View.showSnackBar(
    text: String? = "Something went wrong",
    duration: Int = Snackbar.LENGTH_LONG
) {
    Snackbar.make(this, "", duration).show()

}




