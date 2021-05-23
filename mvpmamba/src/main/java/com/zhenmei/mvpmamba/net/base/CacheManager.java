package com.zhenmei.mvpmamba.net.base;

import android.content.Context;

import com.zchu.rxcache.RxCache;
import com.zchu.rxcache.diskconverter.GsonDiskConverter;

import java.io.File;

public class CacheManager {

    private Context context;

    public RxCache getRxCache() {
        return rxCache;
    }

    private RxCache rxCache;

    public void init(Context context) {

        this.context = context;

        rxCache =  new RxCache.Builder()
                .appVersion(1) //当版本号改变,缓存路径下存储的所有数据都会被清除掉
                .diskDir(new File(getCacheDir().getPath() + File.separator + "data-cache"))
                .diskConverter(new GsonDiskConverter())
                .memoryMax(2*1024*1024)
                .diskMax(20*1024*1024)
                .build();

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
//        return new RxCache.Builder()
//                //.useExpiredDataIfLoaderNotAvailable(true)
//                .persistence(getCacheDir(), new GsonSpeaker())
//                .using(service);


        return null;
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
