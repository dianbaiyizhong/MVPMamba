package com.zhenmei.mvpmamba.demo.mvp.view;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.zhenmei.mvpmamba.demo.R;
import com.zhenmei.mvpmamba.demo.activity.MyBaseActivity;
import com.zhenmei.mvpmamba.demo.mvp.contract.WeatherContract;
import com.zhenmei.mvpmamba.demo.mvp.presenter.WeatherPresenter;

public class WeatherActivity extends MyBaseActivity<WeatherPresenter> implements WeatherContract.MView {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

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

        mPresenter.getWeather();


    }

    @Override
    public void loadSuccess() {

    }

    @Override
    public void loadError() {

    }


}
