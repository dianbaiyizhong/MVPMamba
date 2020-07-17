package com.zhenmei.p7i.mvpmamba;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.zhenmei.p7i.core.activity.BaseActivity;
import com.zhenmei.p7i.mvpmamba.app.MVPApplication;
import com.zhenmei.p7i.core.di.component.AppComponent;
import com.zhenmei.p7i.core.mvp.BasePresenter;
import com.zhenmei.p7i.core.mvp.IView;

import javax.inject.Inject;

public abstract class MVPBaseActivity<P extends BasePresenter> extends BaseActivity implements IView {
    @Inject
    @Nullable
    protected P mPresenter;
    private MVPApplication application;

    protected abstract void componentInject(AppComponent appComponent);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        application = ((MVPApplication) getApplication());
        //设置布局
        componentInject(application.getAppComponent());//依赖注入
        super.onCreate(savedInstanceState);

    }

}
