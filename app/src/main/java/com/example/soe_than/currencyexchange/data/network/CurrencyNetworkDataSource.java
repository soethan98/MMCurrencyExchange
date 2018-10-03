package com.example.soe_than.currencyexchange.data.network;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.soe_than.currencyexchange.AppExecutors;
import com.example.soe_than.currencyexchange.AppSettings;
import com.example.soe_than.currencyexchange.api.ApiClient;
import com.example.soe_than.currencyexchange.service.CurrencySyncIntentService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by soe_than on 5/14/18.
 */

public class CurrencyNetworkDataSource {

    private static final Object LOCK = new Object();
    private static CurrencyNetworkDataSource mInstance;
    private Context context;
    private static String LOG_TAG = CurrencyNetworkDataSource.class.getSimpleName();

    private final MutableLiveData<List<Currency>> currencyLiveData;
    private final AppExecutors appExecutors;
    private final AppSettings appSettings;

    public CurrencyNetworkDataSource(Context context, AppExecutors appExecutors) {
        this.context = context;
        this.appExecutors = appExecutors;
        currencyLiveData = new MutableLiveData<>();

        appSettings = AppSettings.getInstance(context);

    }

    public static CurrencyNetworkDataSource getmInstance(Context context, AppExecutors appExecutors) {
        if (mInstance == null) {
            synchronized (LOCK) {
                mInstance = new CurrencyNetworkDataSource(context, appExecutors);
            }
        }
        return mInstance;
    }

    public LiveData<List<Currency>> fetchCurrencyList() {

        Call<CurrencyExchange> responseCall = ApiClient.create().getCurrencyExchange();
        responseCall.enqueue(new Callback<CurrencyExchange>() {
            @Override
            public void onResponse(Call<CurrencyExchange> call, Response<CurrencyExchange> response) {
                Log.d(LOG_TAG, "PROCESSING IN THREAD BEFORE RETROFIT CALL " + Thread.currentThread().getName());

                currencyLiveData.postValue(response.body().getCurrencyList());
                appSettings.set("TIMESTAMP", System.currentTimeMillis());
            }

            @Override
            public void onFailure(Call<CurrencyExchange> call, Throwable t) {
                Log.e(LOG_TAG, "Error RETROFIT");

            }
        });

        return currencyLiveData;
    }

    public LiveData<List<Currency>> getAllCurrencies() {
        return currencyLiveData;
    }


    public void startService() {
        Intent intentToFetch = new Intent(context, CurrencySyncIntentService.class);
        context.startService(intentToFetch);
        Log.d(LOG_TAG, "Service created");
    }

}
