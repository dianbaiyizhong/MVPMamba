package com.zhenmei.p7i.mvpmamba.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.zhenmei.p7i.core.activity.FMVPActivity;
import com.zhenmei.p7i.core.mvp.BasePresenter;

import javax.inject.Inject;


public abstract class MyBaseActivity<P extends BasePresenter> extends FMVPActivity {


    @Override
    protected boolean enableInject() {
        return true;
    }

    @Inject
    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.attachView(this);
        mPresenter.setContext(this);
    }
}
