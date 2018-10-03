package com.example.soe_than.currencyexchange.ui.list;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.mtp.MtpEvent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.soe_than.currencyexchange.AppSettings;
import com.example.soe_than.currencyexchange.InjectorUtils;
import com.example.soe_than.currencyexchange.R;
import com.example.soe_than.currencyexchange.Utils;
import com.example.soe_than.currencyexchange.api.ApiClient;
import com.example.soe_than.currencyexchange.api.ApiInterface;
import com.example.soe_than.currencyexchange.data.network.Currency;
import com.example.soe_than.currencyexchange.data.network.CurrencyExchange;
import com.example.soe_than.currencyexchange.ui.detail.CalculateActivity;
import com.example.soe_than.currencyexchange.ui.list.adapter.CurrencyAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements CurrencyAdapter.ItemClick {

    private RecyclerView mRecyclerView;
    private ProgressBar mLoading;
    private CurrencyAdapter mCurrencyAdapter;
    private TextView mTextview;
    private MainActivityViewModel mainActivityViewModel;
    private AppSettings appSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recycler_view);
        mTextview = findViewById(R.id.last_sync);
        mLoading = findViewById(R.id.pb_loading_indicator);

        mLoading.setVisibility(View.VISIBLE);

        appSettings = AppSettings.getInstance(this);


        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        mRecyclerView.setLayoutManager(layoutManager);


        mRecyclerView.setHasFixedSize(true);


        mCurrencyAdapter = new CurrencyAdapter(this, this);

        mRecyclerView.setAdapter(mCurrencyAdapter);

        long timeStamp = appSettings.getLong("TIMESTAMP");
        Log.i("Cal", timeStamp + "");
        mTextview.setText("Updated On" + Utils.getDate(timeStamp));

        MainActivityViewModelFactory factory = InjectorUtils.provideMainViewModelFactory(this);

        mainActivityViewModel = ViewModelProviders.of(this, factory).get(MainActivityViewModel.class);

        mainActivityViewModel.getmCurrencyList().observe(this, currencies -> {
            mCurrencyAdapter.swapList(currencies);

            if (currencies != null || currencies.size() != 0) {
                showCurrency();
            } else {
                showProgress();
            }

        });


//

    }

    private void showCurrency() {
        mRecyclerView.setVisibility(View.VISIBLE);
        mLoading.setVisibility(View.GONE);
    }

    private void showProgress() {
        mRecyclerView.setVisibility(View.GONE);
        mLoading.setVisibility(View.VISIBLE);
    }


    @Override
    public void itemClickListener(Currency currency) {
        Intent intent = new Intent(MainActivity.this, CalculateActivity.class);
        intent.putExtra("Currency", currency);
        startActivity(intent);

    }
}

