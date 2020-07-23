package com.zhenmei.p7i.mvpmamba;

import android.app.Activity;
import android.app.Application;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.zhenmei.p7i.core.app.ManBaNetBuilder;
import com.zhenmei.p7i.mvpmamba.di.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import io.reactivex.functions.Consumer;
import io.reactivex.plugins.RxJavaPlugins;

public class BaseApplication extends Application implements HasActivityInjector {
    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        RxJavaPlugins.setErrorHandler(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Logger.d(throwable.getMessage());
            }
        });

        ManBaNetBuilder.init(this)
                .withApiHost("https://aliyun001.p7ik4n.com:8082/rest/api/")
                .withInterceptor(new TokenInterceptor())
                .configure();


        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .tag("p7i-log")
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));

        DaggerAppComponent
                .builder()
                .application(this)
                .build()
                .inject(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}
