package com.zhenmei.p7i.mvpmamba.di;

import com.zhenmei.p7i.core.di.component.AppComponent;
import com.zhenmei.p7i.core.di.scope.ActivityScope;
import com.zhenmei.p7i.mvpmamba.mvp.view.UserActivity;

import dagger.Component;

@ActivityScope
@Component(modules = ActivityModule.class, dependencies = AppComponent.class)
public interface ActivityComponent {

    public void inject(UserActivity activity);


}
