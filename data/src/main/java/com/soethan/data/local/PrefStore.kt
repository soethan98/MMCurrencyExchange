package com.soethan.data.local

import android.content.Context
import android.os.Build
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings_preferences")

interface PrefStore {
    fun isNightMode(): Flow<Boolean>
    suspend fun toggleNightMode()

    suspend fun saveServerUpdateTime(timestamp:Long)
    suspend fun saveSyncTime(timestamp: Long)

    fun getServerUpdateTime(): Flow<Long>
    fun getServerSyncTime(): Flow<Long>
}