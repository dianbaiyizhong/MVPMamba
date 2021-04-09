package com.zhenmei.mvpmamba.mvp;

import android.content.Context;


import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;


public class BasePresenter<M extends IModel, V extends IView> extends BasePagingPresenter implements IPresenter {


    protected final String TAG = this.getClass().getSimpleName();
    protected CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public void unSubscription() {
        mCompositeDisposable.clear();
    }

    protected void addSubscription(Disposable disposable) {
        mCompositeDisposable.add(disposable);
    }

    protected M mModel;
    protected V mView;

    public void setContext(Context context) {
        this.context = context;
    }

    protected Context context;

    public BasePresenter() {
    }

    public BasePresenter(M model) {
        this.mModel = model;

    }

    public void attachView(V mView) {
        this.mView = mView;

    }


}
