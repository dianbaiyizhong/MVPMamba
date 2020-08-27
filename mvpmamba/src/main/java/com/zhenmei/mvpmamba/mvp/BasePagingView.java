package com.zhenmei.mvpmamba.mvp;

import java.util.List;

@Deprecated
public interface BasePagingView<T> extends IView {

    public void loadMore(List<T> list);

}
