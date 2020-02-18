package com.zhenmei.p7i.core.mvp;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


public class BasePresenter<M extends IModel, V extends IView> extends BasePagingPresenter implements Ipresenter {


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

    public BasePresenter() {
    }

    //    @Inject
    public BasePresenter(M model, V mView) {
        this.mModel = model;
        this.mView = mView;
    }


    public M getmModel() {
        return mModel;
    }

    public V getmView() {
        return mView;
    }


}
