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
    public void onSystemError(MambaServiceFault fault) {
        super.onSystemError(fault);
    }


    @Override
    public void onErrorTip(String clientMessage) {
        super.onErrorTip(clientMessage);
    }


}
