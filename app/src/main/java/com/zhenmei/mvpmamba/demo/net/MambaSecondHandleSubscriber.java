package com.zhenmei.mvpmamba.demo.net;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.blankj.utilcode.util.ActivityUtils;
import com.google.android.material.snackbar.Snackbar;
import com.zhenmei.mvpmamba.demo.R;
import com.zhenmei.mvpmamba.errorhandle.ExceptionHandle;

/**
 * 在这里对错误进行统一处理
 */
public abstract class MambaSecondHandleSubscriber<T> extends MambaFirstHandlerSubscriber<T> {
    private Context context;

    public abstract boolean showBySnack();

    public MambaSecondHandleSubscriber(Context context) {

        super(context);
        this.context = context;

    }

    @Override
    public void onP7IError(MambaServiceFault fault) {
        if (fault.getErrorCode() == 401001) {

            //注销操作


        }
        if (showBySnack()) {
            Activity activity = ActivityUtils.getActivityByContext(context);
            View view = activity.getWindow().getDecorView().findViewById(android.R.id.content);
            Snackbar.make(view, R.string.haoxiangchuledianwenti, 3000).show();
        }
    }


    @Override
    public void onP7IErrorMessage(String clientMessage) {

    }

    @Override
    public void onNetError(Throwable e) {
        if (showBySnack()) {
            //访问获得对应的Exception
            ExceptionHandle.ResponseThrowable responseThrowable = ExceptionHandle.handleException(e);
            Activity activity = ActivityUtils.getActivityByContext(context);
            View view = activity.getWindow().getDecorView().findViewById(android.R.id.content);
            Snackbar.make(view, responseThrowable.message, 3000).show();
        }
    }


}
