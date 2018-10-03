package com.example.soe_than.currencyexchange.api;

import com.example.soe_than.currencyexchange.data.network.CurrencyExchange;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by soe_than on 5/10/18.
 */

public interface ApiInterface {

    @GET("latest")
    Call<CurrencyExchange> getCurrencyExchange();
}
