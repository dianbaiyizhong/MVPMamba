package com.zhenmei.mvpmamba.demo.mvp.model.api.service;

import com.zhenmei.mvpmamba.demo.net.BaseResponse;
import com.zhenmei.mvpmamba.demo.mvp.entity.WeatherEntity;

import java.util.Map;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface WeatherService {


    @GET("/weather_mini")
    Observable<BaseResponse<WeatherEntity>> getList(@QueryMap Map<String, String> map);

    @GET("http://192.168.68.201:2408/deviceInfo/list")
    Observable<BaseResponse<WeatherEntity>> test(@QueryMap Map<String, String> map);

}
