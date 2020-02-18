package com.zhenmei.p7i.core.application;

import android.app.Application;

import com.zhenmei.p7i.core.di.module.AppModule;
import com.zhenmei.p7i.core.di.module.ClientModule;
import com.zhenmei.p7i.core.di.module.ServiceModule;

public class MVPBaseApplication extends Application {

    private ClientModule mClientModule;
    private ServiceModule serviceModule;
    private AppModule mAppModule;

//    private AppComponent appComponent;
//
//    public AppComponent getAppComponent() {
//        return appComponent;
//    }

    @Override
    public void onCreate() {
        super.onCreate();

//        appComponent = DaggerAppComponent.builder()
////                .clientModule()
//                //  .serviceModule(getServiceModule())
//                .build();
        this.mClientModule = ClientModule.buidler().build();
        this.serviceModule = new ServiceModule();
        this.mAppModule = new AppModule(this);//提供application


    }


    public ClientModule getClientModule() {
        return mClientModule;
    }

    public AppModule getAppModule() {
        return mAppModule;
    }


    public ServiceModule getServiceModule() {
        return serviceModule;
    }

}
