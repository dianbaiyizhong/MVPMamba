package com.zhenmei.p7i.mvpmamba.mvp.view;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.zhenmei.p7i.core.di.component.AppComponent;
import com.zhenmei.p7i.mvpmamba.MVPBaseActivity;
import com.zhenmei.p7i.mvpmamba.di.ActivityModule;
import com.zhenmei.p7i.mvpmamba.di.DaggerActivityComponent;
import com.zhenmei.p7i.mvpmamba.mvp.contract.UserContract;
import com.zhenmei.p7i.mvpmamba.mvp.presenter.UserPresenter;

public class UserActivity extends MVPBaseActivity<UserPresenter> implements UserContract.MView {


    @Override
    public void loadSuccess() {

    }

    @Override
    public void loadPageError(Throwable throwable) {

    }

    @Override
    public void hideLoadingTip() {

    }

    @Override
    protected void componentInject(AppComponent appComponent) {
        DaggerActivityComponent.builder().appComponent(appComponent).activityModule(new ActivityModule(this, this)).build().inject(this);

    }


    @Override
    public boolean enableRequestedOrientation() {
        return false;
    }

    @Override
    public boolean bindEventBus() {
        return false;
    }

    @Override
    public int initLayout() {
        return 0;
    }

    @Override
    public void initList() {

    }

    @Override
    public void initToolBar() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        mPresenter.getUser();

    }
}
