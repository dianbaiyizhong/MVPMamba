package com.zhenmei.p7i.core.net.base;

import java.io.Serializable;

/**
 * 网络请求结果 基类
 */

public class BaseResponse<T> implements Serializable {
    public int code;
    public String info;

    public T data;
    public Object exData;


    public boolean isSuccess() {
        return code == 0;
    }
}
