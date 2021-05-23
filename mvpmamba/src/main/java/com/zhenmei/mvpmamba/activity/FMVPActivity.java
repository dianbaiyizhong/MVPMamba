package com.zhenmei.mvpmamba.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.zhenmei.mvpmamba.di.AndroidInjection;
import com.zhenmei.mvpmamba.mvp.BasePresenter;
import com.zhenmei.mvpmamba.mvp.IView;

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
