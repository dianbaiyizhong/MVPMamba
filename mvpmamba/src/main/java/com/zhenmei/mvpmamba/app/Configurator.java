package com.zhenmei.mvpmamba.app;

import android.app.Activity;
import android.os.Handler;

//import com.joanzapata.iconify.IconFontDescriptor;
//import com.joanzapata.iconify.Iconify;

import com.google.gson.JsonDeserializer;

import java.util.ArrayList;
import java.util.WeakHashMap;

import okhttp3.Interceptor;


public final class Configurator {

    private static final WeakHashMap<Object, Object> P7I_CONFIGS = new WeakHashMap<>();
    private static final Handler HANDLER = new Handler();
    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();
    private static final ArrayList<JsonDeserializer> JSON_DESERIALIZER = new ArrayList<>();

    static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    private Configurator() {
        P7I_CONFIGS.put(ConfigKeys.CONFIG_READY, false);
        P7I_CONFIGS.put(ConfigKeys.HANDLER, HANDLER);
    }

    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    public final void configure() {
        P7I_CONFIGS.put(ConfigKeys.CONFIG_READY, true);
    }


    final WeakHashMap<Object, Object> getP7IConfigs() {
        return P7I_CONFIGS;
    }


    public final Configurator withApiHost(String host) {
        P7I_CONFIGS.put(ConfigKeys.API_HOST, host);
        return this;
    }


    public final Configurator withInterceptor(Interceptor interceptor) {
        INTERCEPTORS.add(interceptor);
        P7I_CONFIGS.put(ConfigKeys.INTERCEPTOR, INTERCEPTORS);
        return this;
    }


    public final Configurator withJsonDeserializer(JsonDeserializer jsonDeserializer) {
        JSON_DESERIALIZER.add(jsonDeserializer);
        P7I_CONFIGS.put(ConfigKeys.JSON_DESERIALIZER, JSON_DESERIALIZER);
        return this;
    }

    public final Configurator withInterceptors(ArrayList<Interceptor> interceptors) {
        INTERCEPTORS.addAll(interceptors);
        P7I_CONFIGS.put(ConfigKeys.INTERCEPTOR, INTERCEPTORS);
        return this;
    }

    private void checkConfiguration() {
        final boolean isReady = (boolean) P7I_CONFIGS.get(ConfigKeys.CONFIG_READY);
        if (!isReady) {
            throw new RuntimeException("Configuration is not ready,call configure");
        }
    }


    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Object key) {
        checkConfiguration();
        final Object value = P7I_CONFIGS.get(key);
        if (value == null) {
            //throw new NullPointerException(key.toString() + " IS NULL");
            return null;
        }
        return (T) P7I_CONFIGS.get(key);
    }

//    public final Configurator withLoaderDelayed(long delayed) {
//        P7I_CONFIGS.put(ConfigKeys.LOADER_DELAYED, delayed);
//        return this;
//    }


    public final Configurator withActivity(Activity activity) {
        P7I_CONFIGS.put(ConfigKeys.ACTIVITY, activity);
        return this;
    }
}
