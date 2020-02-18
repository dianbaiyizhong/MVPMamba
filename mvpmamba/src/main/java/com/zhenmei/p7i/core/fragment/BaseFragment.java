package com.zhenmei.p7i.core.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.trello.rxlifecycle2.android.FragmentEvent;
import com.zhenmei.p7i.core.integration.lifecycle.FragmentLifecycleable;
import com.zhenmei.p7i.core.swipeback.SwipeBackFragment;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;


public abstract class BaseFragment extends SwipeBackFragment implements FragmentLifecycleable {
    private final BehaviorSubject<FragmentEvent> mLifecycleSubject = BehaviorSubject.create();

    private final static String TAG = "p7i";


    public Fragment getFragment() {
        return fragment;
    }

    private Fragment fragment;

    protected FragmentActivity _mActivity = null;
    private Unbinder mUnbinder = null;

    public abstract Object setLayout();

    public abstract boolean setSwipe();

    public abstract void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView);


    public abstract void initListView(@NonNull View rootView);

    public abstract boolean bindEventBus();

    protected AlertDialog alertDialog;

    @NonNull
    @Override
    public Subject<FragmentEvent> provideLifecycleSubject() {
        return mLifecycleSubject;
    }


    protected View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragment = this;
        if (setLayout() == null) {
            throw new ClassCastException("setLayout不能为空");
        }
        if (setLayout() instanceof Integer) {
            rootView = inflater.inflate((int) setLayout(), container, false);
        } else if (setLayout() instanceof View) {
            rootView = (View) setLayout();
        } else {
            throw new ClassCastException("type of setLayout() must be int or View!");
        }
        mUnbinder = ButterKnife.bind(this, rootView);
        initListView(rootView);
        onBindView(savedInstanceState, rootView);


        if (bindEventBus()) {
            EventBus.getDefault().register(this);
        }

        if (setSwipe()) {
            return attachToSwipeBack(rootView);

        } else {
            return rootView;

        }

    }


    @Override
    public void onResume() {
        Log.d(TAG, "onResume:" + this);
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.d(TAG, "onPause:" + this);

        super.onPause();
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy:" + this);

        super.onDestroy();


        if (alertDialog != null) {
            alertDialog.dismiss();
        }

        if (bindEventBus()) {
            EventBus.getDefault().unregister(this);
        }


    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        Log.d(TAG, "onHiddenChanged:" + this);

        super.onHiddenChanged(hidden);
    }


    @Override
    public void onDestroyView() {
        Log.d(TAG, "onDestroyView:" + this);

        super.onDestroyView();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        if (alertDialog != null) {
            alertDialog.dismiss();
        }

        EventBus.getDefault().unregister(this);

    }


}