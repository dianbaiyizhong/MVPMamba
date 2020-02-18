package com.zhenmei.p7i.core.mvp;


@Deprecated
public interface BaseView {
    /**
     * 显示loading
     */
    void showLoading();

    /**
     * 显示错误
     */
    void showError();


    /**
     * 显示完成
     */
    void showComplete();

}
