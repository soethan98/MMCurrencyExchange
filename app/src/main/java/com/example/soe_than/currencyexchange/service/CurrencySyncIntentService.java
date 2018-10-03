package com.example.soe_than.currencyexchange.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.example.soe_than.currencyexchange.InjectorUtils;
import com.example.soe_than.currencyexchange.data.network.CurrencyNetworkDataSource;

/**
 * Created by soe_than on 5/15/18.
 */

public class CurrencySyncIntentService extends IntentService {

    public CurrencySyncIntentService() {
        super("CurrencySyncIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        CurrencyNetworkDataSource dataSource = InjectorUtils.provideNetworkDataSource(this.getApplicationContext());
        dataSource.fetchCurrencyList();

    }
}
