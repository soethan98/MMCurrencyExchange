package com.example.soe_than.currencyexchange.ui.list;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.example.soe_than.currencyexchange.data.CurrencyRepository;
import com.example.soe_than.currencyexchange.data.network.Currency;

import java.util.List;

/**
 * Created by soe_than on 5/15/18.
 */

public class MainActivityViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final CurrencyRepository mRepository;


    public MainActivityViewModelFactory(CurrencyRepository repository) {
        this.mRepository = repository;

    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new MainActivityViewModel(mRepository);
    }
}
