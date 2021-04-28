package com.soethan.domain.usecase

import com.soethan.domain.repo.ExchangeRepo
import com.soethan.domain.repo.SettingRepo

class ToggleNightMode constructor(private val settingRepo: SettingRepo) : BaseUseCase() {
    suspend fun execute() {
        return settingRepo.toggleNightMode()
    }
}