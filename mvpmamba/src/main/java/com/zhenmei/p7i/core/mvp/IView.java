package com.zhenmei.p7i.core.mvp;

/**
 * Created by jess on 16/4/22.
 */
public interface IView {

    void loadPageError(Throwable throwable);


    void hideLoadingTip();

}
