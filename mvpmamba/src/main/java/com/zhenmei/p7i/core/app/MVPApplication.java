package com.zhenmei.p7i.core.app;

import android.app.Application;

public class MVPApplication extends Application {
//    private ClientModule mClientModule;
//    private ServiceModule serviceModule;
//    private AppModule mAppModule;
//
//    private AppComponent appComponent;
//
//    public AppComponent getAppComponent() {
//        return appComponent;
//    }

    @Override
    public void onCreate() {
        super.onCreate();

//        this.mClientModule = ClientModule.buidler().build();
//        this.serviceModule = new ServiceModule();
//        this.mAppModule = new AppModule(this);//提供application

//        appComponent = DaggerAppComponent.builder()
//                .clientModule(getClientModule())
//                .appModule(getAppModule())
//                .serviceModule(getServiceModule())
//                .build();


    }

//    public ClientModule getClientModule() {
//        return mClientModule;
//    }
//
//    public AppModule getAppModule() {
//        return mAppModule;
//    }
//
//
//    public ServiceModule getServiceModule() {
//        return serviceModule;
//    }

}
