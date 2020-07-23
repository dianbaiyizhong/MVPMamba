package com.zhenmei.p7i.mvpmamba.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.zhenmei.p7i.core.activity.FMVPActivity;
import com.zhenmei.p7i.core.mvp.BasePresenter;
import com.zhenmei.p7i.core.mvp.IView;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public abstract class MyActivity<P extends BasePresenter> extends FMVPActivity implements IView {

    @Inject
    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);

        super.onCreate(savedInstanceState);
    }
}
