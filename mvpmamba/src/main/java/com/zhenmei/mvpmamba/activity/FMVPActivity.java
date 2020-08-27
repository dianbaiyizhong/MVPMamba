package com.zhenmei.mvpmamba.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.zhenmei.mvpmamba.demo.activity.LifecycleActivity;
import com.zhenmei.mvpmamba.di.AndroidInjection;
import com.zhenmei.mvpmamba.demo.mvp.IView;
import com.zhenmei.mvpmamba.mvp.BasePresenter;

public abstract class FMVPActivity<P extends BasePresenter> extends LifecycleActivity implements IView {

    protected abstract boolean enableInject();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (enableInject()) {
            AndroidInjection.inject(this);
        }
        super.onCreate(savedInstanceState);
    }
}
