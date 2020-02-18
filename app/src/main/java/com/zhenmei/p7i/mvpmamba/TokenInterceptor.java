package com.zhenmei.p7i.mvpmamba;


import com.blankj.utilcode.util.DeviceUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TokenInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Request.Builder requestBuilder = originalRequest.newBuilder()
                .addHeader("Accept-Encoding", "gzip")
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .method(originalRequest.method(), originalRequest.body());


        requestBuilder.addHeader("deviceId", DeviceUtils.getUniqueDeviceId());
        requestBuilder.addHeader("deviceName", DeviceUtils.getManufacturer() + ":" + DeviceUtils.getModel());

        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}
