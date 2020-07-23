package com.zhenmei.p7i.mvpmamba.di;

import com.zhenmei.p7i.core.di.scope.FragmentScope;
import com.zhenmei.p7i.mvpmamba.mvp.view.UserActivity;
import com.zhenmei.p7i.mvpmamba.mvp.view.UserFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class BuildersModule {

    @ContributesAndroidInjector(modules = {ActivityModule.class})
    abstract UserActivity bindUserActivity();


    @FragmentScope
    @ContributesAndroidInjector(modules = {ActivityModule.class})
    abstract UserFragment bindUserFragment();
}
