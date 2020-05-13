package com.zhenmei.p7i.mvpmamba;

import com.blankj.utilcode.util.SPStaticUtils;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.zhenmei.p7i.core.app.P7I;
import com.zhenmei.p7i.core.application.MVPApplication;

import io.reactivex.functions.Consumer;
import io.reactivex.plugins.RxJavaPlugins;

public class BaseApplication extends MVPApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        RxJavaPlugins.setErrorHandler(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Logger.d(throwable.getMessage());


            }
        });

        P7I.init(this)
                .withApiHost("https://192.168.88.200/rest/api/")
                .withInterceptor(new TokenInterceptor())

                .withLoaderDelayed(1000)
                .configure();


        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .tag("p7i-log")
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));

    }
}
