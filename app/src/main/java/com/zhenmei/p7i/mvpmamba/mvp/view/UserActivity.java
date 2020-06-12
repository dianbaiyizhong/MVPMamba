package com.zhenmei.p7i.mvpmamba.mvp.view;

import com.orhanobut.logger.Logger;
import com.zhenmei.p7i.core.di.component.AppComponent;
import com.zhenmei.p7i.core.net.base.CommonRetrofitServiceManager;
import com.zhenmei.p7i.mvpmamba.MVPBaseActivity;
import com.zhenmei.p7i.mvpmamba.di.ActivityModule;
import com.zhenmei.p7i.mvpmamba.di.DaggerActivityComponent;
import com.zhenmei.p7i.mvpmamba.mvp.contract.UserContract;
import com.zhenmei.p7i.mvpmamba.mvp.model.api.service.UserService;
import com.zhenmei.p7i.mvpmamba.mvp.presenter.UserPresenter;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

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
//        mPresenter.getUser();

        Map<String, String> map = new HashMap<>();

        map.put("output", "json");
        map.put("location", "1.352083,103.819836");
        map.put("ak", "dlE6GA20ySHpklCY6WAIjsdm");
        map.put("coordtype", "wgs84ll");
        CommonRetrofitServiceManager.getInstance().create(UserService.class)
                .getCountryInfoByBaiDuApi(map)
                .subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())//子线程访问网络
                .observeOn(AndroidSchedulers.mainThread())//回调到主线程
                .subscribe(subject -> {
                    Logger.i("__" + subject.toString());

                }, throwable -> {

                });
        ;

    }
}
