package com.hunfrit.repeattest.main.BaseActivities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hunfrit.repeattest.API.Retrofit.ApiRetrofit;
import com.hunfrit.repeattest.R;
import com.hunfrit.repeattest.main.Presentation.RateForTodayPresenter;
import com.hunfrit.repeattest.main.View.MainView;


/**
 * Created by Artem Shapovalov on 18.12.2017.
 */

public class MainActivity extends AppCompatActivity implements MainView{

    private TextView mRate, mDate;
    private ProgressBar mProgressBar;

    private RateForTodayPresenter mRateForTodayPresenter;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_view);

        mRate = (TextView) findViewById(R.id.rateID);
        mDate = (TextView) findViewById(R.id.dateID);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        mRateForTodayPresenter = new RateForTodayPresenter(this);
        mRateForTodayPresenter.getValue(ApiRetrofit.getRetrofit());

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                stopRefreshing();
                hideElements();
                mRateForTodayPresenter.getValue(ApiRetrofit.getRetrofit());
            }
        });

    }

    @Override
    public void resultForTodayIsSuccessful(float resultForToday, String date) {
        mSwipeRefreshLayout.setRefreshing(false);
        mRate.setText(String.valueOf(resultForToday));
        mDate.setText(date);
        showElements();
    }

    @Override
    public void showError(boolean check, int index) {
        if(check){
            mSwipeRefreshLayout.setRefreshing(false);
            mRate.setText("ERROR");
            mDate.setText("ERROR");

            showCustomDialog(index);
        }
    }

    public void showElements(){
        mProgressBar.setVisibility(View.GONE);
        mRate.setVisibility(View.VISIBLE);
        mDate.setVisibility(View.VISIBLE);
    }

    public void hideElements(){
        mRate.setVisibility(View.GONE);
        mDate.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.VISIBLE);
    }

    public void stopRefreshing(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }, 1000);
    }

    public void showCustomDialog(int number){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        switch (number){
            case 505:   builder.setTitle("Error")
                                    .setMessage("Problem with server")
                                    .setIcon(R.mipmap.ic_launcher)
                                    .setCancelable(false)
                                    .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            dialogInterface.cancel();
                                        }
                                    });
                                    break;
            case 404:   builder.setTitle("Error")
                                    .setMessage("Problem with internet")
                                    .setIcon(R.mipmap.ic_launcher)
                                    .setCancelable(false)
                                    .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            dialogInterface.cancel();
                                        }
                                    });
                                    break;
        }
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
