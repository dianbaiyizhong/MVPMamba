package com.zhenmei.mvpmamba.mvp;


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
