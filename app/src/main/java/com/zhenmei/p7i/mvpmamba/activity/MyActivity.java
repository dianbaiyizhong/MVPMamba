package com.zhenmei.p7i.mvpmamba.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.zhenmei.p7i.core.activity.FMVPActivity;
import com.zhenmei.p7i.core.app.MVPApplication;
import com.zhenmei.p7i.core.mvp.BasePresenter;
import com.zhenmei.p7i.mvpmamba.di.ActivityComponent;
import com.zhenmei.p7i.mvpmamba.di.ActivityModule;
import com.zhenmei.p7i.mvpmamba.di.DaggerActivityComponent;

import java.lang.reflect.Type;

import javax.inject.Inject;

public abstract class MyActivity<P extends BasePresenter> extends FMVPActivity {

    @Inject
    protected P mPresenter;

    protected abstract void initInject(ActivityComponent activityComponent);


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityComponent activityComponent = DaggerActivityComponent.builder()
                .appComponent(((MVPApplication) getApplication()).getAppComponent())
                .activityModule(new ActivityModule(this, this))
                .build();
        initInject(activityComponent);
    }
}
