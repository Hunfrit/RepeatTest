package com.hunfrit.repeattest.API.ApiService;

import com.hunfrit.repeattest.SetGet.SetGet;

import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Artem Shapovalov on 18.12.2017.
 */

public interface ApiRateService {
    @GET("NBUStatService/v1/statdirectory/exchange?valcode=EUR&json")
    Call<List<SetGet>> getRateForToday();
}
