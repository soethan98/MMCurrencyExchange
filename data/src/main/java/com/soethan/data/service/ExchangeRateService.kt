package com.soethan.data.service

import com.soethan.data.model.CurrencyApiModel
import retrofit2.http.GET


interface ExchangeRateService {
    @GET("latest")
    suspend fun getCurrencyExchange(): CurrencyApiModel

}