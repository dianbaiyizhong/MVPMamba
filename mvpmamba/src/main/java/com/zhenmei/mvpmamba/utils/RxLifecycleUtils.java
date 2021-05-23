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

import com.trello.rxlifecycle4.LifecycleTransformer;
import com.trello.rxlifecycle4.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle4.components.support.RxFragment;
import com.zhenmei.mvpmamba.mvp.IView;

import io.reactivex.rxjava3.annotations.NonNull;



@SuppressLint("RestrictedApi")
public class RxLifecycleUtils {
    public static <T> LifecycleTransformer<T> bindToLifecycle(@NonNull IView view) {
        if (view instanceof RxAppCompatActivity) {
            return ((RxAppCompatActivity) view).bindToLifecycle();
        }else{
            return ((RxFragment) view).bindToLifecycle();
        }

    }

    private RxLifecycleUtils() {
        throw new IllegalStateException("you can't instantiate me!");
    }

}
