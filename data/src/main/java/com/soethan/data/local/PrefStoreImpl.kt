package com.soethan.data.local

import android.content.Context
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.longPreferencesKey
import com.soethan.data.util.getData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import timber.log.Timber
import java.io.IOException
import java.sql.Timestamp

class PrefStoreImpl constructor(private val context: Context) : PrefStore {

    override suspend fun toggleNightMode() {
        context.dataStore.edit {
            it[PreferencesKeys.NIGHT_MODE_KEY] = !(it[PreferencesKeys.NIGHT_MODE_KEY] ?: false)
        }
    }

    override suspend fun saveServerUpdateTime(timestamp: Long) {
        context.dataStore.edit {
            it[PreferencesKeys.SERVER_UPDATE_TIME] = timestamp
        }
    }

    override suspend fun saveSyncTime(timestamp: Long) {
        context.dataStore.edit {
            it[PreferencesKeys.SERVER_SYNC_TIME] = timestamp
        }
    }

    override fun getServerUpdateTime() = context.dataStore.getData().map {
        it[PreferencesKeys.SERVER_UPDATE_TIME] ?: 0
    }

    override fun getServerSyncTime() = context.dataStore.getData().map {
        it[PreferencesKeys.SERVER_SYNC_TIME] ?: 0
    }


    override fun isNightMode() = context.dataStore.getData().map {
        it[PreferencesKeys.NIGHT_MODE_KEY] ?: false
    }


    private object PreferencesKeys {
        val NIGHT_MODE_KEY = booleanPreferencesKey("dark_theme_enabled")
        val SERVER_UPDATE_TIME = longPreferencesKey("server_update_time")
        val SERVER_SYNC_TIME = longPreferencesKey("server_sync_time")
    }
}