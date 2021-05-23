package com.zhenmei.mvpmamba.demo.mvp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.zhenmei.mvpmamba.demo.R;
import com.zhenmei.mvpmamba.demo.fragment.MyBaseFragment;
import com.zhenmei.mvpmamba.demo.mvp.contract.WeatherContract;
import com.zhenmei.mvpmamba.demo.mvp.presenter.WeatherPresenter;

public class WeatherFragment extends MyBaseFragment<WeatherPresenter> implements WeatherContract.MView {


    @Override
    public void onViewCreated(@NonNull  View view, @Nullable  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.getWeather();
    }


    @Override
    public View onCreateView(@NonNull  LayoutInflater inflater, @Nullable  ViewGroup container, @Nullable  Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weather_layout,container,false);
    }

    @Override
    public void loadSuccess() {

    }

    @Override
    public void loadError() {

    }

}
