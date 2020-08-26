package com.zhenmei.p7i.mvpmamba.mvp.model;

import com.zhenmei.p7i.core.di.scope.ActivityScope;
import com.zhenmei.p7i.core.mvp.BaseModel;
import com.zhenmei.p7i.mvpmamba.mvp.model.api.subject.CommonListSubject;
import com.zhenmei.p7i.mvpmamba.net.PayLoad;
import com.zhenmei.p7i.mvpmamba.mvp.contract.WeatherContract;
import com.zhenmei.p7i.mvpmamba.mvp.entity.WeatherEntity;
import com.zhenmei.p7i.mvpmamba.mvp.model.api.service.WeatherService;

import java.util.Map;

import io.reactivex.Observable;

@ActivityScope
public class WeatherModel extends BaseModel implements WeatherContract.Model {


    @Override
    public Observable<WeatherEntity> getWeather(Map<String, String> map) {
        return observe(apiService(WeatherService.class).getList(bean2Map(map))).map(new PayLoad<>());
    }
}
