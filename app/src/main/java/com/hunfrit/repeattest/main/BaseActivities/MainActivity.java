package com.hunfrit.repeattest.main.BaseActivities;

import android.media.MediaDataSource;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
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

    private ApiRetrofit mApi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_view);

        mRate = (TextView) findViewById(R.id.rateID);
        mDate = (TextView) findViewById(R.id.dateID);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        mRateForTodayPresenter = new RateForTodayPresenter(this);
        mRateForTodayPresenter.getValue(mApi.getRetrofit());

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                falseRefreshing();
                hideElements();
                mRateForTodayPresenter = new RateForTodayPresenter(MainActivity.this);
                mRateForTodayPresenter.getValue(mApi.getRetrofit());
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
        mSwipeRefreshLayout.setRefreshing(false);
        mRate.setText("ERROR");
        mDate.setText("ERROR");
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

    public void falseRefreshing(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }, 1000);
    }
}
