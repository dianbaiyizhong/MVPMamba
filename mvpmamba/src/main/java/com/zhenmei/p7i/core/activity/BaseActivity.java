package com.zhenmei.p7i.core.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Slide;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;

import com.blankj.utilcode.util.BarUtils;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.zhenmei.p7i.core.integration.lifecycle.ActivityLifecycleable;
import com.zhenmei.p7i.core.swipeback.SwipeBackActivity;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;

@SuppressLint("SourceLockedOrientationActivity")
public abstract class BaseActivity extends SwipeBackActivity implements ActivityLifecycleable {

    private final BehaviorSubject<ActivityEvent> mLifecycleSubject = BehaviorSubject.create();


    private Unbinder mUnbinder = null;

    protected Bundle savedInstanceState;


    @NonNull
    @Override
    public Subject<ActivityEvent> provideLifecycleSubject() {
        return mLifecycleSubject;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else {
            //设置状态栏黑色字体与图标(只支持安卓5.0+，知乎也是这样)
//            QMUIStatusBarHelper.setStatusBarLightMode(this);
            BarUtils.setStatusBarLightMode(this, true);
        }

        super.onCreate(savedInstanceState);

        //禁止旋转
        if (enableRequestedOrientation()) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        this.savedInstanceState = savedInstanceState;

        //设置布局
        if (initLayout() != 0) {
            setContentView(initLayout());
        }
        mUnbinder = ButterKnife.bind(this);
        if (bindEventBus()) {
            EventBus.getDefault().register(this);
        }
//        setupWindowAnimations();
        initList();
        initToolBar();
        //初始化控件
        initView();
        //设置数据
        initData();

        /**
         * 默认不要开启侧滑
         */
        setSwipeBackEnable(false);


    }

    @SuppressWarnings("unchecked")
    void transitionTo(Intent i) {
        final Pair<View, String>[] pairs = TransitionHelper.createSafeTransitionParticipants(this, true);
        ActivityOptionsCompat transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this, pairs);
        startActivity(i, transitionActivityOptions.toBundle());
    }

    private void setupWindowAnimations() {
        Fade fade = new Fade();
        Slide slide = new Slide();
        slide.setDuration(500);
        fade.setDuration(500);
        //这个activity进入的时候动画
        getWindow().setEnterTransition(fade);
        //这个activity退出时的动画
        getWindow().setReturnTransition(slide);


    }

    public abstract boolean enableRequestedOrientation();


    public abstract boolean bindEventBus();


    /**
     * 设置布局
     *
     * @return
     */
    public abstract int initLayout();

    public abstract void initList();

    /**
     * 初始化toolbar
     */
    public abstract void initToolBar();


    /**
     * 初始化布局
     */
    public abstract void initView();

    /**
     * 设置数据
     */
    public abstract void initData();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        EventBus.getDefault().unregister(this);
    }

    public Context getContext() {
        return getActivity();
    }

    public Activity getActivity() {
        return this;
    }


}
