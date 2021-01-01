package com.noura.anwar.finalexam.model;

import android.app.Application;

import java.util.HashMap;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class NetworkUtils {

    private final String BASE_URL = "https://api.exchangeratesapi.io/";
    private static NetworkUtils instance ;
    private CurrencyApiInterface currencyApiInterface ;

    public static NetworkUtils getInstance(Application application) {
        if (instance == null){
            instance = new NetworkUtils(application);
        }
        return instance ;
    }

    private NetworkUtils(Application application){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        currencyApiInterface = retrofit.create(CurrencyApiInterface.class);
    }
    public CurrencyApiInterface getCurrencyApiInterface(){
        return currencyApiInterface;
    }
    public HashMap<String, String> getQueryParams() {
        HashMap<String, String> map = new HashMap<>();
        map.put("latest","based");
        return map ;
    }
}