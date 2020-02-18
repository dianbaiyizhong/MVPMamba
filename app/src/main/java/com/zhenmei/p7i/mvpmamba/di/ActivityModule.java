package com.zhenmei.p7i.mvpmamba.di;

import android.content.Context;

import com.zhenmei.p7i.core.di.scope.ActivityScope;
import com.zhenmei.p7i.core.mvp.IView;
import com.zhenmei.p7i.mvpmamba.mvp.contract.UserContract;
import com.zhenmei.p7i.mvpmamba.mvp.model.UserModel;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private IView view;


    private Context context;

    @Provides
    public Context context() {
        return context;
    }

    public ActivityModule(IView view) {
        this.view = view;
    }

    public ActivityModule(IView view, Context context) {
        this.view = view;
        this.context = context;
    }


    @ActivityScope
    @Provides
    UserContract.MView provideUserView() {
        return (UserContract.MView) view;
    }

    @ActivityScope
    @Provides
    UserContract.Model provideUserModel() {
        return new UserModel();
    }


}