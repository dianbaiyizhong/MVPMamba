//package com.zhenmei.p7i.core.di.module;
//
//import android.app.Activity;
//
//import com.zhenmei.p7i.core.activity.BaseDaggerActivity;
//import com.zhenmei.p7i.core.activity.BaseMVPActivity;
//import com.zhenmei.p7i.core.di.component.ActSubComponent;
//
//import dagger.Binds;
//import dagger.Module;
//import dagger.android.ActivityKey;
//import dagger.android.AndroidInjector;
//import dagger.multibindings.IntoMap;
//
//@Module(subcomponents = {ActSubComponent.class})
//public abstract class BuildersModule {
//    @Binds
//    @IntoMap
//    @ActivityKey(BaseMVPActivity.class)
//    abstract AndroidInjector.Factory<? extends Activity> baseMVPActivity(ActSubComponent.Builder builder);
//}
