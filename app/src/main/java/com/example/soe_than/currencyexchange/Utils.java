package com.example.soe_than.currencyexchange;

import android.widget.ImageView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by soe_than on 5/15/18.
 */

public class Utils {


    public static String convertLongToString(long millisecond) {

        DateFormat df = new SimpleDateFormat("dd:MM:yy:HH:mm:ss");

//
        Calendar cal = Calendar.getInstance();

        cal.setTimeInMillis(millisecond);

        System.out.println("Milliseconds to Date using Calendar:"
                + cal.getTime());

//        System.out.println("Milliseconds to Date using Calendar:"
//                + df.format(cal.getTime()));

        //copying one Date's value into another Date in Java
//        Date now = new Date();
//        Date copiedDate = new Date(now.getTime());
//        System.out.println("original Date: " + df.format(now));
//        System.out.println("copied Date: " + df.format(cal.getTime()));
        return df.format(cal.getTime());


    }



    public static String getDate(long milliSeconds)
    {
//        // Create a DateFormatter object for displaying date in specified format.
//        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
//
//        // Create a calendar object that will convert the date and time value in milliseconds to date.
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(milliSeconds);
//        return formatter.format(calendar.getTime());
        Date date = new Date(milliSeconds);
        SimpleDateFormat simple = new SimpleDateFormat("dd-MM-yyyy HH:mm");

        return simple.format(date);
    }

    public static void setFlag(String countCode, ImageView img) {
        if (countCode.equalsIgnoreCase("United State Dollar")) {

            img.setImageResource(R.drawable.ic_united_states);

        } else if (countCode.equalsIgnoreCase("Euro")) {
            img.setImageResource(R.drawable.ic_european_union);


        } else if (countCode.equalsIgnoreCase("Singapore Dollar")) {
            img.setImageResource(R.drawable.ic_singapore);


        } else if (countCode.equalsIgnoreCase("Pound Sterling")) {
            img.setImageResource(R.drawable.ic_england);


        } else if (countCode.equalsIgnoreCase("Japanese Yen")) {
            img.setImageResource(R.drawable.ic_japan);


        } else if (countCode.equalsIgnoreCase("Australian Dollar")) {

            img.setImageResource(R.drawable.ic_austraila);


        } else if (countCode.equalsIgnoreCase("Bangladesh Taka")) {
            img.setImageResource(R.drawable.ic_bangladesh);


        } else if (countCode.equalsIgnoreCase("Brunei Dollar")) {
            img.setImageResource(R.drawable.ic_brunei);


        } else if (countCode.equalsIgnoreCase("Cambodian Riel")) {
            img.setImageResource(R.drawable.ic_cambodia);


        } else if (countCode.equalsIgnoreCase("Canadian Dollar")) {
            img.setImageResource(R.drawable.ic_canada);


        } else if (countCode.equalsIgnoreCase("Chinese Yuan")) {
            img.setImageResource(R.drawable.ic_china);


        } else if (countCode.equalsIgnoreCase("Hong Kong Dollar")) {
            img.setImageResource(R.drawable.ic_hong_kong);


        } else if (countCode.equalsIgnoreCase("Indonesian Rupiah")) {
            img.setImageResource(R.drawable.ic_indonesia);


        } else if (countCode.equalsIgnoreCase("Indian Rupee")) {
            img.setImageResource(R.drawable.ic_india);


        } else if (countCode.equalsIgnoreCase("Korean Won")) {
            img.setImageResource(R.drawable.ic_korea);

        } else if (countCode.equalsIgnoreCase("Lao Kip")) {
            img.setImageResource(R.drawable.ic_laos);


        } else if (countCode.equalsIgnoreCase("Malaysian Ringgit")) {
            img.setImageResource(R.drawable.ic_malasya);


        } else if (countCode.equalsIgnoreCase("Pakistani Rupee")) {
            img.setImageResource(R.drawable.ic_pakistan);


        } else if (countCode.equalsIgnoreCase("Philippines Peso")) {
            img.setImageResource(R.drawable.ic_philphines);


        } else if (countCode.equalsIgnoreCase("Sri Lankan Rupee")) {
            img.setImageResource(R.drawable.ic_srilanka);


        } else if (countCode.equalsIgnoreCase("Thai Baht")) {
            img.setImageResource(R.drawable.ic_thailand);


        } else if (countCode.equalsIgnoreCase("Vietnamese Dong")) {
            img.setImageResource(R.drawable.ic_vietnam);


        } else if (countCode.equalsIgnoreCase("Egyptian Pound")) {
            img.setImageResource(R.drawable.ic_egypt);


        } else if (countCode.equalsIgnoreCase("Israeli Shekel")) {
            img.setImageResource(R.drawable.ic_israel);


        } else if (countCode.equalsIgnoreCase("Kuwaiti Dinar")) {
            img.setImageResource(R.drawable.ic_kwait);


        } else if (countCode.equalsIgnoreCase("Nepalese Rupee")) {
            img.setImageResource(R.drawable.ic_nepal);


        } else if (countCode.equalsIgnoreCase("Russian Rouble")) {
            img.setImageResource(R.drawable.ic_russia);


        } else if (countCode.equalsIgnoreCase("Saudi Arabian Riyal")) {
            img.setImageResource(R.drawable.ic_saudi);


        }


    }

    public static String getCountryCode(String fullName) {
        if (fullName.equalsIgnoreCase("United State Dollar")) {
            return "USD";
        } else if (fullName.equalsIgnoreCase("Euro")) {
            return "EUR";
        } else if (fullName.equalsIgnoreCase("Singapore Dollar")) {
            return "SGD";
        } else if (fullName.equalsIgnoreCase("Pound Sterling")) {
            return "GBP";
        } else if (fullName.equalsIgnoreCase("Japanese Yen")) {
            return "JPY";
        } else if (fullName.equalsIgnoreCase("Australian Dollar")) {
            return "AUD";
        } else if (fullName.equalsIgnoreCase("Bangladesh Taka")) {
            return "BDT";
        } else if (fullName.equalsIgnoreCase("Brunei Dollar")) {
            return "BND";
        } else if (fullName.equalsIgnoreCase("Cambodian Riel")) {
            return "KHR";
        } else if (fullName.equalsIgnoreCase("Canadian Dollar")) {
            return "CAD";
        } else if (fullName.equalsIgnoreCase("Chinese Yuan")) {
            return "CNY";
        } else if (fullName.equalsIgnoreCase("Hong Kong Dollar")) {
            return "HKD";
        } else if (fullName.equalsIgnoreCase("Indonesian Rupiah")) {
            return "IDR";
        } else if (fullName.equalsIgnoreCase("Indian Rupee")) {
            return "INR";
        } else if (fullName.equalsIgnoreCase("Korean Won")) {
            return "KRW";
        } else if (fullName.equalsIgnoreCase("Lao Kip")) {
            return "LAK";
        } else if (fullName.equalsIgnoreCase("Malaysian Ringgit")) {
            return "MYR";
        } else if (fullName.equalsIgnoreCase("Pakistani Rupee")) {
            return "PKR";
        } else if (fullName.equalsIgnoreCase("Philippines Peso")) {
            return "PHP";
        } else if (fullName.equalsIgnoreCase("Sri Lankan Rupee")) {
            return "LKR";
        } else if (fullName.equalsIgnoreCase("Thai Baht")) {
            return "THB";
        } else if (fullName.equalsIgnoreCase("Vietnamese Dong")) {
            return "VND";
        } else if (fullName.equalsIgnoreCase("Egyptian Pound")) {
            return "EGP";
        } else if (fullName.equalsIgnoreCase("Israeli Shekel")) {
            return "ILS";
        } else if (fullName.equalsIgnoreCase("Kuwaiti Dinar")) {
            return "KWD";
        } else if (fullName.equalsIgnoreCase("Nepalese Rupee")) {
            return "NPR";
        } else if (fullName.equalsIgnoreCase("Russian Rouble")) {
            return "RUB";
        } else if (fullName.equalsIgnoreCase("Saudi Arabian Riyal")) {
            return "SAR";
        }


        return null;
    }
}
