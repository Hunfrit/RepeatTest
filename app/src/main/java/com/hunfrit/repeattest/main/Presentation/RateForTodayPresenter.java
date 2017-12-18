package com.hunfrit.repeattest.main.Presentation;

import android.util.Log;

import com.hunfrit.repeattest.API.ApiService.ApiRateService;
import com.hunfrit.repeattest.SetGet.SetGet;
import com.hunfrit.repeattest.main.View.MainView;

import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.net.HttpURLConnection.HTTP_INTERNAL_ERROR;
import static java.net.HttpURLConnection.HTTP_NOT_FOUND;
import static java.net.HttpURLConnection.HTTP_UNAVAILABLE;

/**
 * Created by Artem Shapovalov on 18.12.2017.
 */

public class RateForTodayPresenter {

    private boolean mCheck;

    private MainView view;

    public RateForTodayPresenter(MainView view){
       this.view = view;
    }

    private List<SetGet> mSetGet;
    private SetGet mPost;

    public void getValue(ApiRateService rate){

        mSetGet = new ArrayList<>();

        rate.getRateForToday().enqueue(new Callback<List<SetGet>>() {
            @Override
            public void onResponse(Call<List<SetGet>> call, Response<List<SetGet>> response) {
                mCheck = false;
                Log.d("TAGA", "FOR TODAY: ");

                if (response.isSuccessful()){
                    Log.d("TAGA", String.valueOf(response.isSuccessful()));
                    if (String.valueOf(response.body()).equals("[]")){

                        mCheck = true;

                        view.showError(mCheck, 404);

                        return;
                    }
                    mSetGet.addAll(response.body());
                    mPost = mSetGet.get(0);
                    view.resultForTodayIsSuccessful(mPost.getRate(), mPost.getExchangedate());
                    Log.d("TAGA", "WORK " + mPost.getRate());
                }else{
                    mCheck = true;
                    Log.d("TAGA", String.valueOf(response.code()));
                    switch (response.code()){
                        case HTTP_NOT_FOUND:
                        case HTTP_UNAVAILABLE:
                        case HTTP_INTERNAL_ERROR:
                            view.showError(mCheck,404);
                            break;
                        default:
                            view.showError(mCheck, 404);
                    }
                }

            }

            @Override
            public void onFailure(Call<List<SetGet>> call, Throwable t) {
                mCheck = true;
                if (t instanceof EOFException){
                    view.showError(mCheck, 505);
                }else if (t instanceof IOException){
                    view.showError(mCheck, 505);
                }else{
                    view.showError(mCheck, 505);
                }
                Log.d("TAGA", String.valueOf(t));

            }
        });
    }
}
