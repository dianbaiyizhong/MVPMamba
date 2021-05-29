package com.zhenmei.mvpmamba.demo.utils;
import com.zhenmei.mvpmamba.demo.net.BaseResponse;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableTransformer;
import io.reactivex.rxjava3.functions.Function;

public class RxResponseUtils {
    public static ObservableTransformer exceptionTransformer() {

        return observable -> observable
                .map(new HandleFuc<>())  //这里可以取出BaseResponse中的Result
                .onErrorResumeNext(new HttpResponseFunc());
    }
    private static class HttpResponseFunc<T> implements Function<Throwable, Observable<T>> {
        @Override
        public Observable<T> apply(Throwable t) {
            return Observable.error(new RuntimeException("报错啦"));
        }
    }

    private static class HandleFuc<T> implements Function<BaseResponse<T>, T> {
        @Override
        public T apply(BaseResponse<T> response) {
            if (!response.isSuccess())
                throw new RuntimeException(!"".equals(response.status + "" + response.desc) ? response.desc : "");
            return response.data;
        }
    }
}
