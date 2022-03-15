package com.soethan.data.source.network.service

import com.soethan.data.source.network.model.CurrencyApiModel
import retrofit2.http.GET


interface ExchangeRateService {
    @GET("latest")
    suspend fun getCurrencyExchange(): CurrencyApiModel

}