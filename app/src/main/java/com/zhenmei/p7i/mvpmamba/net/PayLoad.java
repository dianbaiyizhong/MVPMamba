package com.zhenmei.p7i.mvpmamba.net;

import io.reactivex.functions.Function;

/**
 * 通过请求得到的json里的code等值是否为异常（正常情况是code=0），在这个类中写你的判断逻辑
 */

public class PayLoad<T> implements Function<BaseResponse<T>, T> {

    @Override
    public T apply(BaseResponse<T> tBaseResponse) {

        if (!tBaseResponse.isSuccess()) {

            throw new MambaServiceFault(tBaseResponse.status, tBaseResponse.desc, tBaseResponse.exData);
        }

        return tBaseResponse.data;
    }
}
