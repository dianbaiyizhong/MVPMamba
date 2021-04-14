/*
 * Copyright 2017 JessYan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zhenmei.mvpmamba.utils;

import android.annotation.SuppressLint;

import androidx.core.util.Preconditions;


import com.trello.rxlifecycle4.LifecycleTransformer;
import com.trello.rxlifecycle4.RxLifecycle;
import com.trello.rxlifecycle4.android.ActivityEvent;
import com.trello.rxlifecycle4.android.FragmentEvent;
import com.trello.rxlifecycle4.android.RxLifecycleAndroid;
import com.zhenmei.mvpmamba.integration.lifecycle.ActivityLifeCycleAble;
import com.zhenmei.mvpmamba.integration.lifecycle.FragmentLifeCycleAble;
import com.zhenmei.mvpmamba.integration.lifecycle.LifeCycleAble;
import com.zhenmei.mvpmamba.mvp.IView;
import io.reactivex.rxjava3.annotations.NonNull;



@SuppressLint("RestrictedApi")
public class RxLifecycleUtils {

    private RxLifecycleUtils() {
        throw new IllegalStateException("you can't instantiate me!");
    }

    /**
     * 绑定 Activity 的指定生命周期
     *
     * @param view
     * @param event
     * @param <T>
     * @return
     */
    public static <T> LifecycleTransformer<T> bindUntilEvent(@NonNull final IView view,
                                                             final ActivityEvent event) {
        Preconditions.checkNotNull(view, "view == null");
        if (view instanceof ActivityLifeCycleAble) {
            return bindUntilEvent((ActivityLifeCycleAble) view, event);
        } else {
            throw new IllegalArgumentException("view isn't ActivityLifecycleAble");
        }
    }

    /**
     * 绑定 Fragment 的指定生命周期
     *
     * @param view
     * @param event
     * @param <T>
     * @return
     */
    public static <T> LifecycleTransformer<T> bindUntilEvent(@NonNull final IView view,
                                                             final FragmentEvent event) {
        Preconditions.checkNotNull(view, "view == null");
        if (view instanceof FragmentLifeCycleAble) {
            return bindUntilEvent((FragmentLifeCycleAble) view, event);
        } else {
            throw new IllegalArgumentException("view isn't FragmentLifecycleAble");
        }
    }

    public static <T, R> LifecycleTransformer<T> bindUntilEvent(@NonNull final LifeCycleAble<R> lifecycleable,
                                                                final R event) {
        Preconditions.checkNotNull(lifecycleable, "lifecycleAble == null");
        return RxLifecycle.bindUntilEvent(lifecycleable.provideLifecycleSubject(), event);
    }

    /**
     * 绑定 Activity/Fragment 的生命周期
     *
     * @param view
     * @param <T>
     * @return
     */
    public static <T> LifecycleTransformer<T> bindToLifecycle(@NonNull IView view) {
        Preconditions.checkNotNull(view, "view == null");
        if (view instanceof LifeCycleAble) {
            return bindToLifecycle((LifeCycleAble) view);
        } else {
            throw new IllegalArgumentException("view isn't Lifecycleable");
        }
    }

    public static <T> LifecycleTransformer<T> bindToLifecycle(@NonNull LifeCycleAble lifecycleable) {
        Preconditions.checkNotNull(lifecycleable, "lifecycleable == null");
        if (lifecycleable instanceof ActivityLifeCycleAble) {


            return RxLifecycleAndroid.bindActivity(((ActivityLifeCycleAble) lifecycleable).provideLifecycleSubject());
        } else if (lifecycleable instanceof FragmentLifeCycleAble) {
            return RxLifecycleAndroid.bindFragment(((FragmentLifeCycleAble) lifecycleable).provideLifecycleSubject());
        } else {
            throw new IllegalArgumentException("Lifecycleable not match");
        }
    }
}
