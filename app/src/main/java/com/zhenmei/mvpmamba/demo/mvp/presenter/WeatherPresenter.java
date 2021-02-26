package com.zhenmei.mvpmamba.demo.mvp.presenter;

import com.orhanobut.logger.Logger;
import com.zhenmei.mvpmamba.mvp.BasePresenter;
import com.zhenmei.mvpmamba.utils.RxLifecycleUtils;
import com.zhenmei.mvpmamba.demo.mvp.contract.WeatherContract;
import com.zhenmei.mvpmamba.demo.mvp.entity.WeatherEntity;
import com.zhenmei.mvpmamba.demo.net.MambaHandlerSubscriber;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class WeatherPresenter extends BasePresenter<WeatherContract.Model, WeatherContract.MView> {


    @Inject
    public WeatherPresenter(WeatherContract.Model model) {
        super(model);
    }

    public void getWeather() {

        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("city", "北京");


        mModel.getWeather(paramMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mView))
                .subscribe(new MambaHandlerSubscriber<WeatherEntity>(context) {
                    @Override
                    public void onNext(WeatherEntity subject) {
                        Logger.i(subject.toString() + "");
                        mView.loadSuccess();
                    }


                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Logger.e("报错了:" + e.getMessage() + "");
                        super.onError(e);


                        mView.loadError();
                    }
                });


    }


}
