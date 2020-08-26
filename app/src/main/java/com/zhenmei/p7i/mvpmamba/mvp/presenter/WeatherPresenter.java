package com.zhenmei.p7i.mvpmamba.mvp.presenter;

import com.orhanobut.logger.Logger;
import com.zhenmei.p7i.core.mvp.BasePresenter;
import com.zhenmei.p7i.core.utils.RxLifecycleUtils;
import com.zhenmei.p7i.mvpmamba.mvp.contract.WeatherContract;
import com.zhenmei.p7i.mvpmamba.mvp.entity.WeatherEntity;
import com.zhenmei.p7i.mvpmamba.net.MambaHandlerSubscriber;

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
                    }


                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        e.printStackTrace();
                    }
                });


    }


}
