package com.soethan.data.util

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.*
import java.io.IOException

fun <T> DataStore<T>.getData():Flow<T> {
    return this.data.catch { exception ->
        // dataStore.data throws an IOException when an error is encountered when reading data
        if (exception is IOException) {
            emit(emptyPreferences() as T)
        } else {
            throw exception
        }
    }
}