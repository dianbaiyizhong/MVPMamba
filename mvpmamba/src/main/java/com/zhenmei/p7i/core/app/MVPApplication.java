package com.zhenmei.p7i.core.app;

import android.app.Application;

import com.zhenmei.p7i.core.di.component.AppComponent;
import com.zhenmei.p7i.core.di.component.DaggerAppComponent;
import com.zhenmei.p7i.core.di.module.AppModule;
import com.zhenmei.p7i.core.di.module.ClientModule;
import com.zhenmei.p7i.core.di.module.ServiceModule;

public class MVPApplication extends Application {
    private ClientModule mClientModule;
    private ServiceModule serviceModule;
    private AppModule mAppModule;

    private AppComponent appComponent;

    public AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        this.mClientModule = ClientModule.builder().build();
        this.serviceModule = new ServiceModule();
        this.mAppModule = new AppModule(this);//提供application

        appComponent = DaggerAppComponent.builder()
                .clientModule(getClientModule())
                .appModule(getAppModule())
                .serviceModule(getServiceModule())
                .build();


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
