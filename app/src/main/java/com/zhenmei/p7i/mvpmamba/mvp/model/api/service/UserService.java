package com.zhenmei.p7i.mvpmamba.mvp.model.api.service;

import com.zhenmei.p7i.mvpmamba.net.BaiDuApiResponse;
import com.zhenmei.p7i.mvpmamba.net.BaseResponse;
import com.zhenmei.p7i.mvpmamba.mvp.entity.UserEntity;
import com.zhenmei.p7i.mvpmamba.mvp.model.api.subject.CommonListSubject;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface UserService {
    @GET("user/getRobot")
    Observable<BaseResponse<CommonListSubject<UserEntity>>> getList(@QueryMap Map<String, String> map);

//    @GET("https://api.map.baidu.com/reverse_geocoding/v3/")
//    Observable<BaiDuApiResponse<BaiDuApiEntity>> getCountryInfoByBaiDuApi(@QueryMap Map<String, String> map);

    @GET("https://api.map.baidu.com/reverse_geocoding/v3/")
    Observable<Object> getCountryInfoByBaiDuApi(@QueryMap Map<String, String> map);

}
