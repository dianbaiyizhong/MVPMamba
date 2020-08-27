package com.zhenmei.mvpmamba.demo.mvp.model.api.service;

import com.zhenmei.mvpmamba.demo.net.BaseResponse;
import com.zhenmei.mvpmamba.demo.mvp.entity.WeatherEntity;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface WeatherService {


    @GET("/weather_mini")
    Observable<BaseResponse<WeatherEntity>> getList(@QueryMap Map<String, String> map);

}
