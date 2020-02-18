package com.zhenmei.p7i.core.net.base;


import android.util.Log;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.zhenmei.p7i.core.app.ConfigKeys;
import com.zhenmei.p7i.core.app.P7I;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitServiceManager {
    private static final int DEFAULT_TIME_OUT = 5;//超时时间 5s
    private static final int DEFAULT_READ_TIME_OUT = 10;
    private Retrofit mRetrofit;
    private static final ArrayList<Interceptor> INTERCEPTORS = P7I.getConfiguration(ConfigKeys.INTERCEPTOR);
    private static final ArrayList<JsonDeserializer> JSON_DESERIALIZER = P7I.getConfiguration(ConfigKeys.JSON_DESERIALIZER);

    private RetrofitServiceManager() {

        SSLContext sslContext = SSLUtils.getSslContextForCertificateFile("api_ssl_debug.cer");
        TrustManagerFactory trustManagerFactory = null;
        try {
            trustManagerFactory = TrustManagerFactory.getInstance(
                    TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init((KeyStore) null);

        } catch (NoSuchAlgorithmException | KeyStoreException e) {
            e.printStackTrace();
        }
        TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
        if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
            throw new IllegalStateException("Unexpected default trust managers:"
                    + Arrays.toString(trustManagers));
        }
        X509TrustManager trustManager = (X509TrustManager) trustManagers[0];


        // 创建 OKHttpClient
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        //配置ssl
        builder.sslSocketFactory(sslContext.getSocketFactory(), trustManager)
                .hostnameVerifier((hostname, session) -> true);


        builder.connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);//连接超时时间
        builder.writeTimeout(DEFAULT_READ_TIME_OUT, TimeUnit.SECONDS);//写操作 超时时间
        builder.readTimeout(DEFAULT_READ_TIME_OUT, TimeUnit.SECONDS);//读操作超时时间


        if (INTERCEPTORS != null && !INTERCEPTORS.isEmpty()) {
            for (Interceptor interceptor : INTERCEPTORS) {
                builder.addInterceptor(interceptor);

            }
        }
        GsonBuilder gsonBuilder = new GsonBuilder();
        if (JSON_DESERIALIZER != null && !JSON_DESERIALIZER.isEmpty()) {
            for (JsonDeserializer jsonDeserializer : JSON_DESERIALIZER) {
                Type[] types = jsonDeserializer.getClass().getGenericInterfaces();
                ParameterizedType parameterizedType = (ParameterizedType) types[0];
                gsonBuilder.registerTypeAdapter(parameterizedType.getActualTypeArguments()[0], jsonDeserializer);
            }
        }

        String BASE_URL = P7I.getConfiguration(ConfigKeys.API_HOST);


        // 创建Retrofit
        mRetrofit = new Retrofit.Builder()
                .client(builder.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
                .baseUrl(BASE_URL)
                .build();

    }

    public Retrofit getRetrofit() {

        return mRetrofit;
    }


    private static class SingletonHolder {
        private static final RetrofitServiceManager INSTANCE = new RetrofitServiceManager();
    }

    /**
     * 获取RetrofitServiceManager
     *
     * @return
     */
    public static RetrofitServiceManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * 获取对应的Service
     *
     * @param service Service 的 class
     * @param <T>
     * @return
     */
    public <T> T create(Class<T> service) {
        return mRetrofit.create(service);
    }


}
