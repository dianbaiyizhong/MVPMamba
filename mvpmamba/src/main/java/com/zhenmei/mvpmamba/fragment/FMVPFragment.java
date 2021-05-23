package com.zhenmei.mvpmamba.fragment;

import android.content.Context;

import androidx.annotation.NonNull;

import com.trello.rxlifecycle4.components.support.RxFragment;
import com.zhenmei.mvpmamba.di.AndroidInjection;
import com.zhenmei.mvpmamba.mvp.IView;


public abstract class FMVPFragment extends RxFragment implements  IView {


    protected abstract boolean enableInject();


    @Override
    public void onAttach(@NonNull Context context) {
        if (enableInject()) {
            AndroidInjection.inject(this);
        }
        super.onAttach(context);
    }


}
