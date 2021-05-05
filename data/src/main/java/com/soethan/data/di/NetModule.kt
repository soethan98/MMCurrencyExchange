package com.soethan.data.di

import com.soethan.data.network.service.ExchangeRateService
import org.koin.dsl.module
import retrofit2.Retrofit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory


private const val BASE_URL = "https://forex.cbm.gov.mm/api/"

val NET_MODULE = module {
    single<GsonConverterFactory> { provideGsonConverter() }
    single { provideLoggingInterceptor() }
    single { provideOkHttpClient(get<HttpLoggingInterceptor>()) }
    single<Retrofit> {
        provideRetrofit(get(), get())
    }
    single<ExchangeRateService> { get<Retrofit>().create(ExchangeRateService::class.java) }
}

private fun provideGsonConverter() = GsonConverterFactory.create()
private fun provideLoggingInterceptor() =
    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

private fun provideOkHttpClient(interceptor: HttpLoggingInterceptor) =
    OkHttpClient.Builder().addInterceptor(interceptor).build()


private fun provideRetrofit(gsonConverter: GsonConverterFactory, okHttpClient: OkHttpClient) =
    Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(gsonConverter)
        .client(okHttpClient)
        .build()
