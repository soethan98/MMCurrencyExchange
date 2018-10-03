package com.example.soe_than.currencyexchange.api;

import android.support.annotation.NonNull;

import com.example.soe_than.currencyexchange.AppExecutors;
import com.example.soe_than.currencyexchange.data.network.Currency;
import com.example.soe_than.currencyexchange.data.network.CurrencyExchange;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by soe_than on 5/10/18.
 */

public class ApiClient {

    public static final String BASE_URL = "http://forex.cbm.gov.mm/api/";
    public static Retrofit retrofit = null;
    public static ApiClient instance;

    public static ApiInterface create() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .callbackExecutor(AppExecutors.getInstance().networkIO())
                    .build();
        }
        return retrofit.create(ApiInterface.class);
    }






}
