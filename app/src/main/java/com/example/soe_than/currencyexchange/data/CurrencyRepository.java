package com.example.soe_than.currencyexchange.data;


import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.util.Log;

import com.example.soe_than.currencyexchange.AppExecutors;
import com.example.soe_than.currencyexchange.Connectivity;
import com.example.soe_than.currencyexchange.data.database.CurrencyDao;
import com.example.soe_than.currencyexchange.data.network.Currency;
import com.example.soe_than.currencyexchange.data.network.CurrencyNetworkDataSource;

import java.util.Date;
import java.util.List;

/**
 * Created by soe_than on 5/13/18.
 */

public class CurrencyRepository {
    private Context mContext;
    private final AppExecutors appExecutors;
    private final CurrencyNetworkDataSource source;
    private CurrencyDao currencyDao;
    private boolean sInitializes = false;
    private static CurrencyRepository repository;
    private static final Object LOCK = new Object();
    private static String LOG_TAG = CurrencyRepository.class.getSimpleName();

    public CurrencyRepository(Context context, AppExecutors appExecutors, CurrencyNetworkDataSource
            networkDataSource, CurrencyDao dao) {
        this.appExecutors = appExecutors;
        source = networkDataSource;
        currencyDao = dao;
        mContext = context;

        LiveData<List<Currency>> networkData = source.getAllCurrencies();

        networkData.observeForever(currencies ->
        {
            appExecutors.diskIO().execute(() -> {
                currencyDao.clear();
                Log.d(LOG_TAG, "Old data deleted");
                currencyDao.bulkInsert(currencies);
            });
        });

    }


    public synchronized static CurrencyRepository getRepositoryInstance(Context context, CurrencyDao mDao,
                                                                        CurrencyNetworkDataSource dataSource, AppExecutors app) {

        if (repository == null) {
            synchronized (LOCK) {
                repository = new CurrencyRepository(context, app, dataSource, mDao);
            }
        }
        return repository;
    }

    private void startFetchCurrency() {
        source.startService();
    }

    private boolean isFetchNeeded() {
        if (Connectivity.isConnected(mContext)) {
            return true;
        } else {
            return false;
        }
    }

    private synchronized void initializedData() {

        if (sInitializes) return;
        else sInitializes = true;

        appExecutors.diskIO().execute(() -> {
            if (isFetchNeeded()) {
                startFetchCurrency();
            }
        });
    }


    public LiveData<List<Currency>> getCurrentCurrency() {
        initializedData();
        return currencyDao.getCurrencyList();
    }


}
