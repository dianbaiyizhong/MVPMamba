package com.zhenmei.p7i.mvpmamba.di;

import com.zhenmei.p7i.core.di.scope.FragmentScope;
import com.zhenmei.p7i.mvpmamba.mvp.view.WeatherActivity;
import com.zhenmei.p7i.mvpmamba.mvp.view.WeatherFragment;

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
