package com.zhenmei.p7i.mvpmamba;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.blankj.utilcode.util.ActivityUtils;
import com.google.android.material.snackbar.Snackbar;
import com.zhenmei.p7i.core.errorhandle.BaseErrorHandleSubscriber;
import com.zhenmei.p7i.core.net.base.P7IServerFault;

public abstract class ErrorHandleSubscriber<T> extends BaseErrorHandleSubscriber<T> {
    private Context context;

    public ErrorHandleSubscriber(Context context) {

        super(context);
        this.context = context;

    }

    @Override
    public void onP7IError(P7IServerFault fault) {

    }

    @Override
    public void onP7IErrorMessage(String clientInfo) {
        Activity activity = ActivityUtils.getActivityByContext(context);
        View view = activity.getWindow().getDecorView().findViewById(android.R.id.content);
        Snackbar.make(view, clientInfo, 3000).show();

    }


}
