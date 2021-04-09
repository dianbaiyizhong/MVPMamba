package com.zhenmei.mvpmamba.errorhandle;


import android.content.Context;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;


public abstract class BaseErrorHandleSubscriber<T> implements Observer<T> {
    private Context context;

    public BaseErrorHandleSubscriber(Context context) {
        this.context = context;

    }


    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onComplete() {

    }
}
