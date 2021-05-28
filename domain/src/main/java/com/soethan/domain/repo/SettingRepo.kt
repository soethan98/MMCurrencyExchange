package com.soethan.domain.repo
import kotlinx.coroutines.flow.Flow

interface SettingRepo {
   suspend fun toggleNightMode()
    fun isDarkModeEnable(): Flow<Boolean>
}