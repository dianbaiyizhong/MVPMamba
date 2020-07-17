package com.zhenmei.p7i.mvpmamba.app;

import com.zhenmei.p7i.core.di.component.AppComponent;
import com.zhenmei.p7i.core.di.component.DaggerAppComponent;

public class MVPApplication extends MVPBaseApplication {
    private AppComponent appComponent;

    public AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .clientModule(getClientModule())
                .appModule(getAppModule())
                .serviceModule(getServiceModule())
                .build();


    }
}
