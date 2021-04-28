package com.soethan.domain.repo

import com.sun.org.apache.xpath.internal.operations.Bool
import kotlinx.coroutines.flow.Flow

interface SettingRepo {
   suspend fun toggleNightMode()
    fun isDarkModeEnable(): Flow<Boolean>
}