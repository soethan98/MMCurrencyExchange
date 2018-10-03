package com.example.soe_than.currencyexchange.ui.detail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.soe_than.currencyexchange.R;
import com.example.soe_than.currencyexchange.Utils;
import com.example.soe_than.currencyexchange.data.network.Currency;

import com.fathzer.soft.javaluator.DoubleEvaluator;


public class CalculateActivity extends AppCompatActivity {

    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btnDelete, btnPoint;
    private TextView editText1, editText2;
    private TextView countryCode;
    private Toolbar mToolbar;
    private String text = "";
    private int count = 0;
    private String str = "";
    private DoubleEvaluator evaluator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculate_layout);

        btn0 = findViewById(R.id.btn_0);
        btn1 = findViewById(R.id.btn_1);
        btn2 = findViewById(R.id.btn_2);
        btn3 = findViewById(R.id.btn_3);
        btn4 = findViewById(R.id.btn_4);
        btn5 = findViewById(R.id.btn_5);
        btn6 = findViewById(R.id.btn_6);
        btn7 = findViewById(R.id.btn_7);
        btn8 = findViewById(R.id.btn_8);
        btn9 = findViewById(R.id.btn_9);
        btnPoint = findViewById(R.id.btn_point);
        btnDelete = findViewById(R.id.btn_del);
        editText1 = findViewById(R.id.edt1);
        editText2 = findViewById(R.id.edt2);
        countryCode = findViewById(R.id.country_code1);
        mToolbar = findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Currency currency = getIntent().getParcelableExtra("Currency");
        editText1.setText(String.valueOf(1));
        editText2.setText(currency.getRate());
        countryCode.setText(Utils.getCountryCode(currency.getName()));

        calcualteRate(currency);


    }


    private void calcualteRate(Currency currency) {
        editText1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                String calString = editText1.getText().toString();
                String getString = currency.getRate();
                Double result = 0.0;

                if (getString.contains(",")) {
                    str = getString.replace(",", "");
                } else {
                    str = getString;

                }

                try {
                    Double db1 = Double.parseDouble(str);
                    Double db2 = Double.parseDouble(calString);

                    result = db1 * db2;


                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                String s = String.format("%.3f\n", result);
//

                editText2.setText(con(s));


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_0:
                editText1.setText(editText1.getText() + "0");
//                calcualteRate();

                break;
            case R.id.btn_1:
                editText1.setText(editText1.getText() + "1");
//                calcualteRate();

                break;
            case R.id.btn_2:
                editText1.setText(editText1.getText() + "2");
//                calcualteRate();

                break;
            case R.id.btn_3:
                editText1.setText(editText1.getText() + "3");
//                calcualteRate();

                break;
            case R.id.btn_4:
                editText1.setText(editText1.getText() + "4");
//                calcualteRate();

                break;
            case R.id.btn_5:
                editText1.setText(editText1.getText() + "5");
//                calcualteRate();

                break;
            case R.id.btn_6:
                editText1.setText(editText1.getText() + "6");
//                calcualteRate();

                break;
            case R.id.btn_7:
                editText1.setText(editText1.getText() + "7");
//                calcualteRate();

                break;
            case R.id.btn_8:
                editText1.setText(editText1.getText() + "8");

                break;
            case R.id.btn_9:
                editText1.setText(editText1.getText() + "9");

                break;
            case R.id.btn_point:
                if (count == 0 && editText1.length() != 0) {
                    editText1.setText(editText1.getText() + ".");

                    count++;

                }

                break;
            case R.id.btn_del:
                text = editText1.getText().toString();
                if (text.length() > 0) {
                    if (text.endsWith(".")) {
                        count = 0;
                    }

                    String newText = text.substring(0, text.length() - 1);
                    editText1.setText(newText);
                    if (newText.length() == 0) {
                        editText2.setText("");
                    }
                }
                if (text.length() == 0) {
                    editText2.setText("");
                }

                break;

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }


    public String con(String str) {
        int floatPos = str.indexOf(".") > -1 ? str.length() - str.indexOf(".") : 0;
        int nGroups = (str.length() - floatPos - 1 - (str.indexOf("-") > -1 ? 1 : 0)) / 3;
        for (int i = 0; i < nGroups; i++) {
            int commaPos = str.length() - i * 4 - 3 - floatPos;
            str = str.substring(0, commaPos) + "," + str.substring(commaPos, str.length());
        }
        return str;
    }

}
