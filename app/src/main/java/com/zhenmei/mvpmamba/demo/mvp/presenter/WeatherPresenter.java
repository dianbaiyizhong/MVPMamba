package com.zhenmei.mvpmamba.demo.mvp.presenter;

import com.orhanobut.logger.Logger;
import com.zhenmei.mvpmamba.demo.mvp.contract.WeatherContract;
import com.zhenmei.mvpmamba.demo.mvp.entity.WeatherEntity;
import com.zhenmei.mvpmamba.demo.net.MambaWeatherBusinessHandlerSubscriber;
import com.zhenmei.mvpmamba.mvp.BasePresenter;
import com.zhenmei.mvpmamba.utils.RxLifecycleUtils;
import com.zhenmei.mvpmamba.utils.RxUtils;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

public class WeatherPresenter extends BasePresenter<WeatherContract.Model, WeatherContract.MView> {


    @Inject
    public WeatherPresenter(WeatherContract.Model model) {
        super(model);
    }

    public void getWeather() {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("city", "北京");


        /**
         * 统一处理返回信息，无论是成功还是异常，常用于表格增删查改等非常死板的业务
         */
//        mModel.getWeather(paramMap)
//                .compose(RxUtils.schedulersTransformer())
//                .compose(RxLifecycleUtils.bindToLifecycle(mView))
//                .subscribe(new MambaCommonBusinessHandleSubscriber<WeatherEntity>(context));


                mModel.getWeather(paramMap)
                .compose(RxUtils.schedulersTransformer())
                .compose(RxLifecycleUtils.bindToLifecycle(mView))
                .subscribe(new MambaWeatherBusinessHandlerSubscriber<WeatherEntity>(context) {
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
