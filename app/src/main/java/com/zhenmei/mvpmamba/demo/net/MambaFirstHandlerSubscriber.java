package com.zhenmei.mvpmamba.demo.net;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.StringUtils;
import com.orhanobut.logger.Logger;
import com.zhenmei.mvpmamba.errorhandle.BaseErrorHandleSubscriber;

/**
 * 第一层剥离，将异常分为
 * 1，系统异常
 * 2，正常的错误提醒异常
 * 3，状态码异常
 */
public abstract class MambaFirstHandlerSubscriber<T> extends BaseErrorHandleSubscriber<T> {
    private Context context;

    public MambaFirstHandlerSubscriber(Context context) {
        super(context);
        this.context = context;
    }

    // 系统异常
    public abstract void onSystemError(MambaServiceFault fault);

    // 正常的错误提醒异常
    public abstract void onErrorTip(String clientMessage);

    // 网络异常
    public abstract void onNetError(Throwable fault);


    @Override
    public void onError(Throwable e) {
        Logger.e("MambaFirstHandlerSubscriber onError", e);

        // 判断是否为后台返回的正常异常信息
        if (e instanceof MambaServiceFault) {
            MambaServiceFault fault = (MambaServiceFault) e;
            String clientInfo = JSON.parseObject(JSON.toJSONString(fault.getResponseData())).getString("clientInfo");
            if (!StringUtils.isEmpty(clientInfo)) {
                onErrorTip(clientInfo);
            }
            onSystemError(fault);
        } else {
            // 否则当网络异常处理
            onNetError(e);
        }


    }


}
