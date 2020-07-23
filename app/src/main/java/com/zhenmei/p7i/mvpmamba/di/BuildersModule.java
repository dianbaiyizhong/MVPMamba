package com.zhenmei.p7i.mvpmamba.di;

import com.zhenmei.p7i.mvpmamba.mvp.view.UserActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class BuildersModule {

    @ContributesAndroidInjector(modules = {ActivityModule.class})
    abstract UserActivity bindUserActivity();


}
