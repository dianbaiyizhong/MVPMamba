package com.zhenmei.p7i.core.errorhandle;


import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.blankj.utilcode.util.ActivityUtils;
import com.google.android.material.snackbar.Snackbar;
import com.zhenmei.p7i.core.net.base.P7IServerFault;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class BaseErrorHandleSubscriber<T> implements Observer<T> {
    private Context context;

    public BaseErrorHandleSubscriber(Context context) {
        this.context = context;
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof P7IServerFault) {
            P7IServerFault fault = (P7IServerFault) e;
            onP7IError(fault);
            return;
        }

        if (e instanceof Exception) {
            //访问获得对应的Exception
            e.printStackTrace();
            ExceptionHandle.ResponeThrowable responeThrowable = ExceptionHandle.handleException(e);
            Activity activity = ActivityUtils.getActivityByContext(context);
            View view = activity.getWindow().getDecorView().findViewById(android.R.id.content);
            Snackbar.make(view, responeThrowable.message, 3000).show();
        } else {
            e.printStackTrace();
        }

    }

    public abstract void onP7IError(P7IServerFault fault);

    public abstract void onP7IErrorMessage(String clientInfo);


    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onComplete() {

    }
}
