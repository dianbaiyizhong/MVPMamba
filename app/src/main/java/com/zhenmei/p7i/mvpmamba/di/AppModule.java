package com.zhenmei.p7i.mvpmamba.di;

import android.content.Context;

import androidx.annotation.Nullable;

import com.zhenmei.p7i.core.mvp.Ipresenter;
import com.zhenmei.p7i.mvpmamba.BaseApplication;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    @Provides
    Context provideContext(BaseApplication application) {
        return application.getApplicationContext();
    }

    @Provides
    @Nullable
    public static Ipresenter providerPresenter() {
        return null;
    }
}
