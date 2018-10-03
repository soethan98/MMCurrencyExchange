package com.example.soe_than.currencyexchange.data.network;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by soe_than on 5/12/18.
 */

public class CurrencyExchange {
    @SerializedName("info")
    private String info;
    @SerializedName("timestamp")
    private long timestamp;
    @SerializedName("description")
    private String description;
    @SerializedName("rates")
    private Rate ratesBean;

    public CurrencyExchange(String info, long timestamp, String description, Rate ratre) {
        this.info = info;
        this.timestamp = timestamp;
        this.description = description;
        this.ratesBean = ratre;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Rate getRate() {
        return ratesBean;
    }

    public void setRatre(Rate rate) {
        this.ratesBean = rate;
    }


    public List<Currency> getCurrencyList() {

        Log.i("MainActivity", "HII");
        List<Currency> currencyList = new ArrayList<>();

        currencyList.add(new Currency("United State Dollar", ratesBean.getUSD()));
        currencyList.add(new Currency("Euro", ratesBean.getEUR()));
        currencyList.add(new Currency("Singapore Dollar", ratesBean.getSGD()));
        currencyList.add(new Currency("Pound Sterling", ratesBean.getGBP()));
        currencyList.add(new Currency("Japanese Yen", ratesBean.getJPY()));

        currencyList.add(new Currency("Australian Dollar", ratesBean.getAUD()));
        currencyList.add(new Currency("Bangladesh Taka", ratesBean.getBDT()));
        currencyList.add(new Currency("Brunei Dollar", ratesBean.getBND()));
        currencyList.add(new Currency("Cambodian Riel", ratesBean.getKHR()));
        currencyList.add(new Currency("Canadian Dollar", ratesBean.getCAD()));

        currencyList.add(new Currency("Chinese Yuan", ratesBean.getCNY()));
        currencyList.add(new Currency("Hong Kong Dollar", ratesBean.getHKD()));
        currencyList.add(new Currency("Indonesian Rupiah", ratesBean.getIDR()));
        currencyList.add(new Currency("Indian Rupee", ratesBean.getINR()));
        currencyList.add(new Currency("Korean Won", ratesBean.getKRW()));

        currencyList.add(new Currency("Lao Kip", ratesBean.getLAK()));
        currencyList.add(new Currency("Malaysian Ringgit", ratesBean.getMYR()));
        currencyList.add(new Currency("New Zealand Dollar", ratesBean.getNZD()));
        currencyList.add(new Currency("Pakistani Rupee", ratesBean.getPKR()));
        currencyList.add(new Currency("Philippines Peso", ratesBean.getPHP()));

        currencyList.add(new Currency("Sri Lankan Rupee", ratesBean.getLKR()));
        currencyList.add(new Currency("Thai Baht", ratesBean.getTHB()));
        currencyList.add(new Currency("Vietnamese Dong", ratesBean.getVND()));
        currencyList.add(new Currency("Egyptian Pound", ratesBean.getEGP()));
        currencyList.add(new Currency("Israeli Shekel", ratesBean.getILS()));

        currencyList.add(new Currency("Kuwaiti Dinar", ratesBean.getKWD()));
        currencyList.add(new Currency("Nepalese Rupee", ratesBean.getNPR()));
        currencyList.add(new Currency("Russian Rouble", ratesBean.getRUB()));
        currencyList.add(new Currency("Saudi Arabian Riyal", ratesBean.getSAR()));


        return currencyList;
    }
}
