package com.zhenmei.mvpmamba.demo.net;

import android.content.Context;

public abstract class MambaHandlerSubscriber<T> extends MambaSecondHandleSubscriber<T> {
    private Context context;

    public MambaHandlerSubscriber(Context context) {
        super(context);
        this.context = context;
    }


    @Override
    public boolean showBySnack() {
        return false;
    }


    @Override
    public void onP7IError(MambaServiceFault fault) {
        super.onP7IError(fault);
    }


    @Override
    public void onP7IErrorMessage(String clientMessage) {
        super.onP7IErrorMessage(clientMessage);
    }
}
