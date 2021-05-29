package com.zhenmei.mvpmamba.demo.net;

import com.alibaba.fastjson.JSON;

/**
 * 业务异常处理类
 */

public class MambaBusinessException extends RuntimeException {
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


    public MambaBusinessException(int errorCode, String info, Object data) {
        super(JSON.toJSONString(data));
        this.errorCode = errorCode;
        this.responseData = data;
        this.info = info;
    }

}
