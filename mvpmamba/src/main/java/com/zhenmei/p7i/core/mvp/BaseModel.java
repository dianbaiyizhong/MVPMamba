package com.zhenmei.p7i.core.mvp;

import com.blankj.utilcode.util.GsonUtils;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.zhenmei.p7i.core.net.base.RetrofitServiceManager;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class BaseModel {
    public BaseModel() {
    }

    protected static Map<String, String> bean2Map(Object o) {
        String s1 = GsonUtils.toJson(o, false);

        Map<String, String> map2 = GsonUtils.fromJson(s1,
                new TypeToken<Map<String, String>>() {
                }.getType());


        if (map2 == null) {
            return Collections.emptyMap();
        } else {
            return map2;

        }

    }

    protected static RequestBody generateRequestBody(Object object) {
        Gson gson = new Gson();
        String obj = gson.toJson(object);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), obj);
        return body;
    }

    protected static Map<String, RequestBody> generateRequestBody(Map<String, String> requestDataMap) {
        Map<String, RequestBody> requestBodyMap = new HashMap<>();
        for (String key : requestDataMap.keySet()) {
            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"),
                    requestDataMap.get(key) == null ? "" : requestDataMap.get(key));
            requestBodyMap.put(key, requestBody);
        }
        return requestBodyMap;
    }


    protected <T> Observable<T> observe(Observable<T> observable) {
        return observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    protected <T> T apiService(Class<T> service) {

        return RetrofitServiceManager.getInstance().create(service);
    }

}

