package com.zhenmei.p7i.mvpmamba.net;

import android.content.Context;

public abstract class P7IHandlerSubscriber<T> extends P7ISecondHandleSubscriber<T> {
    private Context context;

    public P7IHandlerSubscriber(Context context) {
        super(context);
        this.context = context;
    }


    @Override
    public boolean showBySnack() {
        return false;
    }


    @Override
    public void onP7IError(P7IServerFault fault) {
        super.onP7IError(fault);
    }


    @Override
    public void onP7IErrorMessage(String clientMessage) {
        super.onP7IErrorMessage(clientMessage);
    }
}
