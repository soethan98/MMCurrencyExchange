package com.soethan.data.repository

import com.soethan.data.source.local.PrefStore
import com.soethan.domain.repo.SettingRepo

class SettingRepoImpl constructor(private val prefStore: PrefStore) : SettingRepo {

    override fun isDarkModeEnable() = prefStore.isNightMode()

    override suspend fun toggleNightMode() {
        prefStore.toggleNightMode()
    }
}