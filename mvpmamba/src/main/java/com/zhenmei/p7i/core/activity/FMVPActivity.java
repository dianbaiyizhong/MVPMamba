package com.zhenmei.p7i.core.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.zhenmei.p7i.core.mvp.BasePresenter;

import dagger.android.AndroidInjection;

public abstract class FMVPActivity<P extends BasePresenter> extends BaseMVPActivity {

    //    @Inject
//    protected P mPresenter;
    @Override
    protected boolean enableInject() {
        return true;
    }

//    @Override
//    protected void componentInject(AppComponent appComponent) {
//
//    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }
}
