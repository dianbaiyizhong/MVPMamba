package com.zhenmei.mvpmamba.demo.net;


import io.reactivex.rxjava3.functions.Function;

/**
 * 通过请求得到的json里的code等值是否为异常（正常情况是code=0），在这个类中写你的判断逻辑
 */

public class PayLoad<T> implements Function<BaseResponse<T>, T> {

    @Override
    public T apply(BaseResponse<T> response) {

        if (!response.isSuccess()) {
            throw new MambaServiceFault(response.status, response.desc, response.exData);
        }
        return response.data;
    }
}
