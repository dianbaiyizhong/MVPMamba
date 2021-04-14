package com.zhenmei.mvpmamba.net.base;

import com.zhenmei.mvpmamba.app.ConfigKeys;
import com.zhenmei.mvpmamba.app.ManBaNetBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class CommonRetrofitServiceManager {
    private static final int DEFAULT_CONNECT_TIME = 10;
    private static final int DEFAULT_WRITE_TIME = 30;
    private static final int DEFAULT_READ_TIME = 30;
    private final OkHttpClient okHttpClient;
    private final Retrofit retrofit;

    private CommonRetrofitServiceManager() {

        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(DEFAULT_CONNECT_TIME, TimeUnit.SECONDS)//连接超时时间
                .writeTimeout(DEFAULT_WRITE_TIME, TimeUnit.SECONDS)//设置写操作超时时间
                .readTimeout(DEFAULT_READ_TIME, TimeUnit.SECONDS)//设置读操作超时时间
                .build();

        String BASE_URL = ManBaNetBuilder.getConfiguration(ConfigKeys.API_HOST);

        retrofit = new Retrofit.Builder()
                .client(okHttpClient)//设置使用okhttp网络请求
                .baseUrl(BASE_URL)//设置服务器路径
                .addConverterFactory(GsonConverterFactory.create())//添加转化库，默认是Gson
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())//添加回调库，采用RxJava
                .build();

    }

    private static class SingletonHolder {
        private static final CommonRetrofitServiceManager INSTANCE = new CommonRetrofitServiceManager();
    }

    /*
     * 获取RetrofitServiceManager
     **/
    public static CommonRetrofitServiceManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public <T> T create(Class<T> service) {
        return retrofit.create(service);
    }
}
