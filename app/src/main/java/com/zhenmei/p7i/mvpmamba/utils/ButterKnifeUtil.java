package com.zhenmei.p7i.mvpmamba.utils;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blankj.utilcode.util.ActivityUtils;
import com.zhenmei.p7i.core.utils.LifeCycleHolder;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 大灯泡 on 2019/8/19
 * <p>
 * Description：增加lifecycle监听
 */
public class ButterKnifeUtil {
    private static final String TAG = "ButterKnifeUtil";

    public static void bind(@NonNull Object target, @NonNull View source) {
        LifeCycleHolder.handle(ActivityUtils.getActivityByContext(source.getContext()), ButterKnife.bind(target, source), new LifeCycleHolder.Callback<Unbinder>() {
            @Override
            public void onDestroy(@Nullable Unbinder obj) {
                try {
                    if (obj != null) {
                        obj.unbind();
                    }
                } catch (Exception e) {
                }
            }
        });
    }
}
