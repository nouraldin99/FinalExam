package com.noura.anwar.finalexam.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;
import com.noura.anwar.finalexam.R;
import com.noura.anwar.finalexam.model.CustomAdapter;
import com.noura.anwar.finalexam.model.entity.ApiError;
import com.noura.anwar.finalexam.model.entity.CurrencyInfo;
import com.noura.anwar.finalexam.model.entity.Result;
import com.noura.anwar.finalexam.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {
    private Spinner spinner;
    private Spinner spinner1;
    private TextInputEditText textInputEditTextAmount;
    private MaterialTextView textInputEditTextResult;
    private String currencyTo ;
    private int positionFrom ;
    private int positionTo ;
    private CurrencyInfo currencyInfo ;
    private String base ;
    private MainViewModel mainViewModel ;
    private  String[] currencyNames;
    private int [] flags ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textInputEditTextAmount = findViewById(R.id.edit_text_amount);
        textInputEditTextResult = findViewById(R.id.edit_text_result);
        spinner = findViewById(R.id.spinner);
        spinner1 = findViewById(R.id.spinner1);

        currencyNames = getResources().getStringArray(R.array.currency);
        flags = getFlags();
        CustomAdapter customAdapter=new CustomAdapter(getApplicationContext(),flags, currencyNames);
        spinner.setAdapter(customAdapter);
        spinner1.setAdapter(customAdapter);
        spinner1.setSelection(2);



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            base = currencyNames[i];
            positionFrom =i ;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                currencyTo = currencyNames[i];
                positionTo =  i ;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        textInputEditTextAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                convertCurrency();

            }

            @Override
            public void afterTextChanged(Editable editable) {
                convertCurrency();
            }
        });

    }

    private void convertCurrency(){
        if (!TextUtils.isEmpty(textInputEditTextAmount.getText().toString())){
        mainViewModel = new MainViewModel(getApplication());
        mainViewModel.getCurrencyInfo(base).addObserver((observable, o) -> {
            Result result = (Result) o;
            switch (result.status){
                case SUCCESS:
                    currencyInfo = (CurrencyInfo) result.data;
                    if (currencyInfo != null){
                        double amount  = Integer.parseInt(String.valueOf(textInputEditTextAmount.getText()));
                        textInputEditTextResult.setText(String.valueOf(calculateCurrency(amount)));

                    }
            break;
                case ERROR:
                    ApiError apiError = (ApiError) result.data;
                    Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),apiError.getMessage(),Snackbar.LENGTH_LONG);
                    if (apiError.isRecoverable()){
                        snackbar.setAction("Retry",view -> mainViewModel.getCurrencyInfo(base));
                    }
                    snackbar.show();

            }
        });

        }else {
            textInputEditTextAmount.setError("Fill this Field");
        }
    }

    public double calculateCurrency(double amount){
        double result ;
        double currencyToDouble = 0 ;
        switch (currencyTo){
            case "CAD":
                currencyToDouble = currencyInfo.getRates().getCAD();
                break;
            case "HKD":
                 currencyToDouble = currencyInfo.getRates().getHKD();
            break;
            case "ISK":
                currencyToDouble = currencyInfo.getRates().getISK();
                break;
            case "PHP":
                currencyToDouble = currencyInfo.getRates().getPHP();
                break;
            case "DKK":
                currencyToDouble = currencyInfo.getRates().getDKK();
                break;
            case "CZK":
                currencyToDouble = currencyInfo.getRates().getCZK();
                break;
            case "RON":
                currencyToDouble = currencyInfo.getRates().getRON();
                break;
            case "SEK":
                currencyToDouble = currencyInfo.getRates().getSEK();
                break;
            case "IDR":
                currencyToDouble = currencyInfo.getRates().getIDR();
                break;
            case "INR":
                currencyToDouble = currencyInfo.getRates().getINR();
                break;
            case "BRL":
                currencyToDouble = currencyInfo.getRates().getBRL();
                break;
            case "RUB":
                currencyToDouble = currencyInfo.getRates().getRUB();
                break;
            case "HRK":
                currencyToDouble = currencyInfo.getRates().getHRK();
                break;
                case "CHF":
                currencyToDouble = currencyInfo.getRates().getCHF();
                break;
            case "EUR":
                currencyToDouble = currencyInfo.getRates().getEUR();
                break;
            case "MYR":
                currencyToDouble = currencyInfo.getRates().getMYR();
                break;
            case "BGN":
                currencyToDouble = currencyInfo.getRates().getBGN();
                break;
            case "TRY":
                currencyToDouble = currencyInfo.getRates().getTRY();
                break;

            case "CNY":
                currencyToDouble = currencyInfo.getRates().getCNY();
                break;
            case "NOK":
                currencyToDouble = currencyInfo.getRates().getNOK();
                break;
            case "NZD":
                currencyToDouble = currencyInfo.getRates().getNZD();
                break;
            case "ZAR":
                currencyToDouble = currencyInfo.getRates().getZAR();
                break;
            case "USD":
                currencyToDouble = currencyInfo.getRates().getUSD();
                break;
            case "MXN":
                currencyToDouble = currencyInfo.getRates().getMXN();
                break;
            case "SGD":
                currencyToDouble = currencyInfo.getRates().getSGD();
                break;
            case "AUD":
                currencyToDouble = currencyInfo.getRates().getAUD();
                break;
            case "ILS":
                currencyToDouble = currencyInfo.getRates().getILS();
                break;
            case "KRW":
                currencyToDouble = currencyInfo.getRates().getKRW();
                break;
            case "PLN":
                currencyToDouble = currencyInfo.getRates().getPLN();
                break;
        }
        result = amount * currencyToDouble ;
        return result ;
    }


    public int [] getFlags(){
        return new int[]{
                R.drawable.ic_cad,
                R.drawable.ic_hkd,
                R.drawable.ic_isk,
                R.drawable.ic_php,
                R.drawable.ic_dkk,
                R.drawable.ic_huf,
                R.drawable.ic_czk,
                R.drawable.ic_rom,
                R.drawable.ic_sek,
                R.drawable.ic_idr,
                R.drawable.ic_inr,
                R.drawable.ic_brl,
                R.drawable.ic_rub,
                R.drawable.ic_hrk,
                R.drawable.ic_jpy,
                R.drawable.ic_thb,
                R.drawable.ic_chf,
                R.drawable.ic_eur,
                R.drawable.ic_myr,
                R.drawable.ic_bgn,
                R.drawable.ic_tru,
                R.drawable.ic_cnu,
                R.drawable.ic_nok,
                R.drawable.ic_nzd,
                R.drawable.ic_zar,
                R.drawable.ic_usd,
                R.drawable.ic_mxn,
                R.drawable.ic_sgd,
                R.drawable.ic_aud,
                R.drawable.ic_ils,
                R.drawable.ic_krw,
                R.drawable.ic_pln
        };
    }

}