package com.zhenmei.p7i.core.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.zhenmei.p7i.core.mvp.BasePresenter;
import com.zhenmei.p7i.core.mvp.IView;

import dagger.android.AndroidInjection;

public abstract class BaseMVPActivity<P extends BasePresenter> extends LifecycleActivity {


//    protected abstract void componentInject(AppComponent appComponent);

    protected abstract boolean enableInject();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (enableInject()) {
            //设置布局
//            AndroidInjection.inject(this);

//            componentInject(((MVPApplication) getApplication()).getAppComponent());//依赖注入
        }
        super.onCreate(savedInstanceState);

    }

}
