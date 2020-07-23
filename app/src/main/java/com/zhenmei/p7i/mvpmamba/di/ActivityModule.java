package com.zhenmei.p7i.mvpmamba.di;

import com.zhenmei.p7i.mvpmamba.mvp.contract.UserContract;
import com.zhenmei.p7i.mvpmamba.mvp.model.UserModel;

import dagger.Module;
import dagger.Provides;


@Module
public class ActivityModule {


    @Provides
    UserContract.Model provideUserModel() {
        return new UserModel();
    }


}