package com.zhenmei.p7i.mvpmamba.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.zhenmei.p7i.core.activity.FMVPActivity;
import com.zhenmei.p7i.core.mvp.BasePresenter;

import javax.inject.Inject;

public abstract class MyActivity<P extends BasePresenter> extends FMVPActivity {

    @Inject
    protected P mPresenter;


    @Override
    public void loadPageError(Throwable throwable) {

    }

    @Override
    public void hideLoadingTip() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
