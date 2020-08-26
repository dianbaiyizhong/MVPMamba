package com.zhenmei.p7i.mvpmamba.di;

import com.zhenmei.p7i.mvpmamba.BaseApplication;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        BuildersModule.class})
public interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(BaseApplication application);

        AppComponent build();
    }

    void inject(BaseApplication app);

}
