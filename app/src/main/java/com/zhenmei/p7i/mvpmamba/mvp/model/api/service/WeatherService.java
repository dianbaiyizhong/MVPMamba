package com.zhenmei.p7i.mvpmamba.mvp.model.api.service;

import com.zhenmei.p7i.mvpmamba.net.BaseResponse;
import com.zhenmei.p7i.mvpmamba.mvp.entity.WeatherEntity;
import com.zhenmei.p7i.mvpmamba.mvp.model.api.subject.CommonListSubject;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface WeatherService {


    @GET("/weather_mini")
    Observable<BaseResponse<WeatherEntity>> getList(@QueryMap Map<String, String> map);

}
