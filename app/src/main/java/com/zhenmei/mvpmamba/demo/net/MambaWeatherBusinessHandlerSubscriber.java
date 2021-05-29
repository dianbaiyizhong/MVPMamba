package com.zhenmei.mvpmamba.demo.net;

import android.content.Context;

public abstract class MambaWeatherBusinessHandlerSubscriber<T> extends MambaCommonBusinessHandleSubscriber<T> {
    private Context context;

    public MambaWeatherBusinessHandlerSubscriber(Context context) {
        super(context);
        this.context = context;
    }


    @Override
    public void onErrorTip(int code, String clientMessage) {
        super.onErrorTip(code,clientMessage);
    }


}
