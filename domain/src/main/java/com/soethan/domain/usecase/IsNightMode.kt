package com.soethan.domain.usecase

import com.soethan.domain.repo.ExchangeRepo
import com.soethan.domain.repo.SettingRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class IsNightMode constructor(private val settingRepo: SettingRepo) : BaseUseCase() {
     fun execute(): Flow<Boolean> {
        return settingRepo.isDarkModeEnable()
    }
}