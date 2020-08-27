package com.zhenmei.mvpmamba.errorhandle;


import android.content.Context;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

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
