package com.zhenmei.p7i.core.net.base;

import android.content.Context;

import java.io.File;

import io.rx_cache2.internal.RxCache;
import io.victoralbertos.jolyglot.GsonSpeaker;

public class CacheManager {

    private Context context;

    public void init(Context context) {

        this.context = context;

    }


    private File getCacheDir() {
        return this.context.getCacheDir();
    }

    /**
     * 获取对应的Service
     *
     * @param service Service 的 class
     * @param <T>
     * @return
     */
    public <T> T create(Class<T> service) {
        return new RxCache.Builder()
                //.useExpiredDataIfLoaderNotAvailable(true)
                .persistence(getCacheDir(), new GsonSpeaker())
                .using(service);
    }


    private static class SingletonHolder {
        private static final CacheManager INSTANCE = new CacheManager();
    }

    /**
     * 获取RetrofitServiceManager
     *
     * @return
     */
    public static CacheManager getInstance() {
        return CacheManager.SingletonHolder.INSTANCE;
    }

}
