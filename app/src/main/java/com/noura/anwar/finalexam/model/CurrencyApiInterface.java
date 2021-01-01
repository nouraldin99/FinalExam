package com.noura.anwar.finalexam.model;

import com.noura.anwar.finalexam.model.entity.CurrencyInfo;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface CurrencyApiInterface {

    @GET("latest")
    Call<CurrencyInfo>getCurrencyInfo(@QueryMap HashMap<String ,String>queryParams);
}
