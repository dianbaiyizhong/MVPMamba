package com.zhenmei.mvpmamba.demo.mvp.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.orhanobut.logger.Logger;
import com.zhenmei.mvpmamba.demo.R;
import com.zhenmei.mvpmamba.demo.activity.MyBaseActivity;
import com.zhenmei.mvpmamba.demo.mvp.contract.WeatherContract;
import com.zhenmei.mvpmamba.demo.mvp.presenter.WeatherPresenter;

public class WeatherActivity extends MyBaseActivity<WeatherPresenter> implements WeatherContract.MView {


    private Button btn_test;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_layout);
        btn_test = findViewById(R.id.btn_test);
        btn_test.setOnClickListener(view -> {
            mPresenter.getWeather();
        });

    }

    @Override
    public void loadSuccess() {

    }



    @Override
    public void loadError() {

    }


}
