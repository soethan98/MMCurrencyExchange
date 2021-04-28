package com.soethan.data.repository

import com.soethan.data.local.PrefStore
import com.soethan.domain.repo.SettingRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber

class SettingRepoImpl constructor(private val prefStore: PrefStore) : SettingRepo {

    override fun isDarkModeEnable() = prefStore.isNightMode()

    override suspend fun toggleNightMode() {
        prefStore.toggleNightMode()
    }
}