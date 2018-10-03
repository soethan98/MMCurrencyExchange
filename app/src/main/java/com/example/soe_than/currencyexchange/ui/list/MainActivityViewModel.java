package com.example.soe_than.currencyexchange.ui.list;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.soe_than.currencyexchange.data.CurrencyRepository;
import com.example.soe_than.currencyexchange.data.network.Currency;

import java.util.List;

/**
 * Created by soe_than on 5/15/18.
 */

public class MainActivityViewModel extends ViewModel {

    private final LiveData<List<Currency>> mCurrencyList;
    private final CurrencyRepository mRepository;

    public MainActivityViewModel(CurrencyRepository repository) {
        mRepository = repository;

        mCurrencyList = mRepository.getCurrentCurrency();
    }

    public LiveData<List<Currency>> getmCurrencyList() {
        return mCurrencyList;
    }


}
