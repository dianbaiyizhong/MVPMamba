package com.zhenmei.p7i.core.di.component;

import android.app.Application;

import com.zhenmei.p7i.core.di.module.AppModule;
import com.zhenmei.p7i.core.di.module.ClientModule;
import com.zhenmei.p7i.core.di.module.ServiceModule;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {AppModule.class, ClientModule.class, ServiceModule.class})
public interface AppComponent {

    Application Application();

}
