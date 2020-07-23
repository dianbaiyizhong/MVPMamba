package com.zhenmei.p7i.core.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.zhenmei.p7i.core.mvp.BasePresenter;
import com.zhenmei.p7i.core.mvp.IView;

public abstract class FMVPActivity<P extends BasePresenter> extends LifecycleActivity implements IView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
