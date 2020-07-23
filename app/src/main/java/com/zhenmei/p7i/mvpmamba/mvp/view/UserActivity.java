package com.zhenmei.p7i.mvpmamba.mvp.view;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.zhenmei.p7i.mvpmamba.activity.MyActivity;
import com.zhenmei.p7i.mvpmamba.di.ActivityComponent;
import com.zhenmei.p7i.mvpmamba.mvp.contract.UserContract;
import com.zhenmei.p7i.mvpmamba.mvp.presenter.UserPresenter;


public class UserActivity extends MyActivity<UserPresenter> implements UserContract.MView {


    @Override
    public void loadSuccess() {


    }


    @Override
    protected void initInject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mPresenter.getUser();


//        Map<String, String> map = new HashMap<>();
//
//        map.put("output", "json");
//        map.put("location", "1.352083,103.819836");
//        map.put("ak", "dlE6GA20ySHpklCY6WAIjsdm");
//        map.put("coordtype", "wgs84ll");
//        CommonRetrofitServiceManager.getInstance().create(UserService.class)
//                .getCountryInfoByBaiDuApi(map)
//                .subscribeOn(Schedulers.io())
//                .subscribeOn(Schedulers.newThread())//子线程访问网络
//                .observeOn(AndroidSchedulers.mainThread())//回调到主线程
//                .subscribe(subject -> {
//                    Logger.i(subject.toString());
//
//                }, throwable -> {
//
//                });
    }

    @Override
    public void loadPageError(Throwable throwable) {

    }

    @Override
    public void hideLoadingTip() {

    }
}
