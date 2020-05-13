package com.zhenmei.p7i.mvpmamba.net;

import com.alibaba.fastjson.JSON;

/**
 * 异常处理类，将异常包装成一个 P7IServerFault ,抛给上层统一处理
 */

public class P7IServerFault extends RuntimeException {
    private int errorCode;
    private Object responseData;
    private String info;

    public Object getResponseData() {
        return responseData;
    }


    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public void setResponseData(Object responseData) {
        this.responseData = responseData;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getErrorCode() {
        return errorCode;
    }


    public P7IServerFault(int errorCode, String info, Object data) {
        super(JSON.toJSONString(data));
        this.errorCode = errorCode;
        this.responseData = data;
        this.info = info;
    }

}
