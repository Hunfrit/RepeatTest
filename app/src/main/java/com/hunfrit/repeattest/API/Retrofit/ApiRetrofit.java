package com.hunfrit.repeattest.API.Retrofit;

import com.hunfrit.repeattest.API.ApiService.ApiRateService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.hunfrit.repeattest.Constants.Constants.*;

/**
 * Created by Artem Shapovalov on 18.12.2017.
 */

public class ApiRetrofit {

    public static ApiRateService getRetrofit(){

        ApiRateService rate;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        rate = retrofit.create(ApiRateService.class);

        return rate;
    }
}
