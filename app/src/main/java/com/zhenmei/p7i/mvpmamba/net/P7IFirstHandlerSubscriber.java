package com.zhenmei.p7i.mvpmamba.net;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.StringUtils;
import com.orhanobut.logger.Logger;
import com.zhenmei.p7i.core.errorhandle.BaseErrorHandleSubscriber;
import com.zhenmei.p7i.core.errorhandle.ExceptionHandle;

/**
 * 第一层剥离，将异常分为1，系统异常  2，系统正常信息异常 3，网络异常
 */
public abstract class P7IFirstHandlerSubscriber<T> extends BaseErrorHandleSubscriber<T> {
    private Context context;

    public P7IFirstHandlerSubscriber(Context context) {
        super(context);
        this.context = context;
    }

    public abstract void onP7IError(P7IServerFault fault);

    public abstract void onNetError(Throwable fault);


    public abstract void onP7IErrorMessage(String clientMessage);


    @Override
    public void onError(Throwable e) {
        Logger.e(JSON.toJSONString(e));
        if (e instanceof P7IServerFault) {
            P7IServerFault fault = (P7IServerFault) e;

            String clientInfo = JSON.parseObject(JSON.toJSONString(fault.getResponseData())).getString("clientInfo");
            if (StringUtils.isEmpty(clientInfo)) {


            } else {
                onP7IErrorMessage(clientInfo);
            }
            onP7IError(fault);
        } else {
            onNetError(e);
        }


    }


}
