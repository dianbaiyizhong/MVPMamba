package com.zhenmei.mvpmamba.demo.di;

import com.zhenmei.mvpmamba.di.scope.FragmentScope;
import com.zhenmei.mvpmamba.demo.mvp.view.WeatherActivity;
import com.zhenmei.mvpmamba.demo.mvp.view.WeatherFragment;
import com.zhenmei.mvpmamba.di.scope.FragmentScope;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class BuildersModule {

    @ContributesAndroidInjector(modules = {ActivityModule.class})
    abstract WeatherActivity bindWeatherActivity();


    @FragmentScope
    @ContributesAndroidInjector(modules = {ActivityModule.class})
    abstract WeatherFragment bindWeatherFragment();
}
