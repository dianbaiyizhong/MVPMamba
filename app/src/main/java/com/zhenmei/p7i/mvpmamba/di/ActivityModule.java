package com.zhenmei.p7i.mvpmamba.di;

import com.zhenmei.p7i.mvpmamba.mvp.contract.WeatherContract;
import com.zhenmei.p7i.mvpmamba.mvp.model.WeatherModel;

import dagger.Module;
import dagger.Provides;


@Module
public class ActivityModule {


    @Provides
    WeatherContract.Model provideWeatherModel() {
        return new WeatherModel();
    }


}