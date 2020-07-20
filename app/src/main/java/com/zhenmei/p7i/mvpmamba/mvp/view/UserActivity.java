package com.zhenmei.p7i.mvpmamba.mvp.view;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.orhanobut.logger.Logger;
import com.zhenmei.p7i.core.di.component.AppComponent;
import com.zhenmei.p7i.core.net.base.CommonRetrofitServiceManager;
import com.zhenmei.p7i.mvpmamba.activity.MyActivity;
import com.zhenmei.p7i.mvpmamba.di.ActivityModule;
import com.zhenmei.p7i.mvpmamba.di.DaggerActivityComponent;
import com.zhenmei.p7i.mvpmamba.mvp.contract.UserContract;
import com.zhenmei.p7i.mvpmamba.mvp.model.api.service.UserService;
import com.zhenmei.p7i.mvpmamba.mvp.presenter.UserPresenter;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class UserActivity extends MyActivity<UserPresenter> implements UserContract.MView {


    @Override
    public void loadSuccess() {


    }

    @Override
    protected void componentInject(AppComponent appComponent) {
        DaggerActivityComponent.builder().appComponent(appComponent)
                .activityModule(new ActivityModule(this, this)).build()
                .inject(this);
    }

//    @Override
//    protected void componentInject(AppComponent appComponent) {
//        DaggerActivityComponent.builder().appComponent(appComponent).activityModule(new ActivityModule(this, this)).build().inject(this);
//
//    }

    @Override
    protected boolean enableInject() {
        return true;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


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
                    Logger.i(subject.toString());

                }, throwable -> {

                });
    }
}
