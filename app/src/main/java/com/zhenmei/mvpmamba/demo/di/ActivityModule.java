package com.zhenmei.mvpmamba.demo.di;

import com.zhenmei.mvpmamba.demo.mvp.contract.WeatherContract;
import com.zhenmei.mvpmamba.demo.mvp.model.WeatherModel;

import dagger.Module;
import dagger.Provides;


@Module
public class ActivityModule {


    @Provides
    WeatherContract.Model provideWeatherModel() {
        return new WeatherModel();
    }


}