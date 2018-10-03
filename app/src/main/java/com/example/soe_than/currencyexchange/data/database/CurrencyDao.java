package com.example.soe_than.currencyexchange.data.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.soe_than.currencyexchange.data.network.Currency;

import java.util.Date;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by soe_than on 5/13/18.
 */
@Dao
public interface CurrencyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void bulkInsert(List<Currency> currencies);

    @Query("SELECT * FROM  currency")
    LiveData<List<Currency>> getCurrencyList();

    @Query("DELETE  FROM currency")
    void clear();

}
