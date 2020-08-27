package com.zhenmei.mvpmamba.demo;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.zhenmei.mvpmamba.app.MVPApplication;
import com.zhenmei.mvpmamba.app.ManBaNetBuilder;
import com.zhenmei.mvpmamba.demo.di.DaggerAppComponent;

/**
 * 请按照这个类，扩展自己的Application类
 */
public class BaseApplication extends MVPApplication {


    @Override
    public void onCreate() {
        super.onCreate();


        /**
         * 配置网络
         */
        ManBaNetBuilder.init(this)
                .withApiHost("http://wthrcdn.etouch.cn/")
//                .withInterceptor(new TokenInterceptor())
                .configure();


        /**
         * dagger必写，照抄即可
         */
        DaggerAppComponent
                .builder()
                .application(this)
                .build()
                .inject(this);


        /**
         * 日志配置，写不写随便你
         */
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .tag("mamba")
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
    }


}
