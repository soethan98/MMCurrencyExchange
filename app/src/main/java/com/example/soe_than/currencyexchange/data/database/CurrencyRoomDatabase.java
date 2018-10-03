package com.example.soe_than.currencyexchange.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.soe_than.currencyexchange.data.network.Currency;

/**
 * Created by soe_than on 5/13/18.
 */
@Database(entities = {Currency.class}, version = 1)
public abstract class CurrencyRoomDatabase extends RoomDatabase{
    private static final String LOG_TAG = CurrencyRoomDatabase.class.getSimpleName();
    private static final String DATABASE_NAME = "curr";

    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static CurrencyRoomDatabase sInstance;

    public static CurrencyRoomDatabase getInstance(Context context) {
        Log.d(LOG_TAG, "Getting the database");
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        CurrencyRoomDatabase.class, DATABASE_NAME).build();
                Log.d(LOG_TAG, "Made new database");
            }
        }
        return sInstance;
    }

    public abstract CurrencyDao mDao();


}
