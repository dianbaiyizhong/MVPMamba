package com.zhenmei.mvpmamba.utils;

import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

public class LifeCycleHolder<T> implements LifecycleObserver {

    protected T obj;
    private Callback<T> callback;

    public static <T> LifeCycleHolder<T> empty() {
        return new LifeCycleHolder<>(null, null);
    }

    public static <T> LifeCycleHolder handle(Object who, T obj, Callback<T> callback) {
        if (who instanceof LifecycleOwner) {
            LifeCycleHolder<T> result = new LifeCycleHolder<T>(obj, callback);
            ((LifecycleOwner) who).getLifecycle().addObserver(result);
            return result;
        }
        return empty();
    }

    private LifeCycleHolder(T obj, Callback<T> callback) {
        this.obj = obj;
        this.callback = callback;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate() {
        if (callback != null) {
            callback.onCreate(obj);
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {
        if (callback != null) {
            callback.onStart(obj);
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        if (callback != null) {
            callback.onResume(obj);
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        if (callback != null) {
            callback.onPause(obj);
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {
        if (callback != null) {
            callback.onStop(obj);
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        if (callback != null) {
            callback.onDestroy(obj);
        }

        obj = null;
        callback = null;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    public void onAny() {
        if (callback != null) {
            callback.onAny(obj);
        }
    }


    public static abstract class Callback<T> {
        public void onCreate(@Nullable T obj) {
        }

        public void onStart(@Nullable T obj) {
        }

        public void onResume(@Nullable T obj) {
        }

        public void onPause(@Nullable T obj) {
        }

        public void onStop(@Nullable T obj) {
        }

        public void onDestroy(@Nullable T obj) {
        }

        public void onAny(@Nullable T obj) {
        }
    }
}
