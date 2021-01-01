package com.noura.anwar.finalexam.viewmodel;

import android.app.Application;


import com.noura.anwar.finalexam.model.NetworkUtils;
import com.noura.anwar.finalexam.model.entity.CurrencyInfo;
import com.noura.anwar.finalexam.model.entity.ObservableCurrencyInfo;
import com.noura.anwar.finalexam.model.entity.ObservableResult;
import com.noura.anwar.finalexam.model.entity.Result;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel {

    private NetworkUtils networkUtils;
    private ObservableResult observableResult;

    public MainViewModel(Application application) {
        networkUtils = NetworkUtils.getInstance(application);
        observableResult = new ObservableResult();
    }

    public ObservableResult getCurrencyInfo(String base) {
        HashMap<String, String> params = networkUtils.getQueryParams();
        networkUtils.getCurrencyApiInterface().getCurrencyInfo(params).enqueue(new Callback<CurrencyInfo>() {
            @Override
            public void onResponse(Call<CurrencyInfo> call, Response<CurrencyInfo> response) {
                if (response.isSuccessful()) {
                    observableResult.setResult(Result.success(response.body()));
            }

            }
            @Override
            public void onFailure(Call<CurrencyInfo> call, Throwable t) {

            }
        });
            return observableResult;

    }
}