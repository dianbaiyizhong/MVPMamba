package com.zhenmei.mvpmamba.demo.net;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.blankj.utilcode.util.ActivityUtils;
import com.google.android.material.snackbar.Snackbar;
import com.zhenmei.mvpmamba.demo.R;
import com.zhenmei.mvpmamba.errorhandle.ExceptionHandle;

import io.reactivex.rxjava3.annotations.NonNull;

/**
 * 在很多业务，例如表格的增删查改，错误等提示信息都是使用相同组件提示，这里可以统一处理
 */
public  class MambaCommonBusinessHandleSubscriber<T> extends MambaFirstHandlerSubscriber<T> {
    private Context context;


    public MambaCommonBusinessHandleSubscriber(Context context) {

        super(context);
        this.context = context;

    }

    @Override
    public void onNext(@NonNull T t) {
        Activity activity = ActivityUtils.getActivityByContext(context);
        View view = activity.getWindow().getDecorView().findViewById(android.R.id.content);
        Snackbar.make(view, "操作成功", 3000).show();
    }

    @Override
    public void onErrorTip(int code, String clientMessage) {

        Activity activity = ActivityUtils.getActivityByContext(context);
        View view = activity.getWindow().getDecorView().findViewById(android.R.id.content);
        Snackbar.make(view, clientMessage, 3000).show();

    }

    @Override
    public void onNetError(Throwable e) {
        //访问获得对应的Exception
        ExceptionHandle.ResponseThrowable responseThrowable = ExceptionHandle.handleException(e);
        Activity activity = ActivityUtils.getActivityByContext(context);
        View view = activity.getWindow().getDecorView().findViewById(android.R.id.content);
        Snackbar.make(view, responseThrowable.message, 3000).show();

    }


}
