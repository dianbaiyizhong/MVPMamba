package com.zhenmei.mvpmamba.demo.mvp.model;

import com.zhenmei.mvpmamba.mvp.BaseModel;
import com.zhenmei.mvpmamba.demo.net.PayLoad;
import com.zhenmei.mvpmamba.demo.mvp.contract.WeatherContract;
import com.zhenmei.mvpmamba.demo.mvp.entity.WeatherEntity;
import com.zhenmei.mvpmamba.demo.mvp.model.api.service.WeatherService;

import java.util.Map;

import io.reactivex.Observable;

public class WeatherModel extends BaseModel implements WeatherContract.Model {

    @Override
    public Observable<WeatherEntity> getWeather(Map<String, String> map) {
        return observe(apiService(WeatherService.class).getList(bean2Map(map))).map(new PayLoad<>());
    }
}
