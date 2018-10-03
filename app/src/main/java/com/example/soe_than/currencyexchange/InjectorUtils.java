package com.example.soe_than.currencyexchange;

import android.content.Context;

import com.example.soe_than.currencyexchange.data.CurrencyRepository;
import com.example.soe_than.currencyexchange.data.database.CurrencyRoomDatabase;
import com.example.soe_than.currencyexchange.data.network.CurrencyNetworkDataSource;
import com.example.soe_than.currencyexchange.ui.list.MainActivityViewModelFactory;

import java.util.Date;

/**
 * Created by soe_than on 5/15/18.
 */

public class InjectorUtils {

    public static CurrencyRepository provideRepository(Context context) {
        CurrencyRoomDatabase database = CurrencyRoomDatabase.getInstance(context.getApplicationContext());
        AppExecutors executors = AppExecutors.getInstance();
        CurrencyNetworkDataSource networkDataSource =
                CurrencyNetworkDataSource.getmInstance(context.getApplicationContext(), executors);
        return CurrencyRepository.getRepositoryInstance(context, database.mDao(), networkDataSource, executors);
    }

    //
    public static CurrencyNetworkDataSource provideNetworkDataSource(Context context) {
        AppExecutors executors = AppExecutors.getInstance();
        provideRepository(context);
        return CurrencyNetworkDataSource.getmInstance(context.getApplicationContext(), executors);
    }

    //
    public static MainActivityViewModelFactory provideMainViewModelFactory(Context context) {
        CurrencyRepository repository = provideRepository(context.getApplicationContext());
        return new MainActivityViewModelFactory(repository);
    }
}
