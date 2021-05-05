package com.soethan.data.network.service

import com.soethan.data.network.model.CurrencyApiModel
import retrofit2.http.GET


interface ExchangeRateService {
    @GET("latest")
    suspend fun getCurrencyExchange(): CurrencyApiModel

}