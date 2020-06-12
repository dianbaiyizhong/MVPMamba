package com.zhenmei.p7i.mvpmamba.net;

import java.io.Serializable;

public class BaiDuApiResponse implements Serializable {


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    private int status;
    private Object result;
}
