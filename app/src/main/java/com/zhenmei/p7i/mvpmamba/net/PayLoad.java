package com.zhenmei.p7i.mvpmamba.net;

import com.zhenmei.p7i.core.net.base.BaseResponse;

import io.reactivex.functions.Function;

/**
 * 剥离 最终数据
 */

public class PayLoad<T> implements Function<BaseResponse<T>, T> {

    @Override
    public T apply(BaseResponse<T> tBaseResponse) {

        if (!tBaseResponse.isSuccess()) {

            throw new P7IServerFault(tBaseResponse.code, tBaseResponse.info, tBaseResponse.exData);
        }

        return tBaseResponse.data;
    }
}
