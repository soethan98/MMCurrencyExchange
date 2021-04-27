package com.soethan.data.local

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException


class PrefManager constructor(private val pref: SharedPreferences) {


    fun save(key: String, value: Long) {
        pref.edit {
            putLong(key, value)
            commit()
        }
    }

    fun getValue(key: String): Long {
        return pref.getLong(key, 0)
    }


    companion object {
        const val SERVER_UPDATE_TIME = "server_update_time"
        const val SERVER_SYNC_TIME = "server_sync_time"
    }


}

