package com.zhenmei.p7i.mvpmamba.mvp.presenter;

import android.content.Context;

import com.orhanobut.logger.Logger;
import com.zhenmei.p7i.core.mvp.BasePresenter;
import com.zhenmei.p7i.core.utils.RxLifecycleUtils;
import com.zhenmei.p7i.mvpmamba.net.P7IHandlerSubscriber;
import com.zhenmei.p7i.mvpmamba.net.P7ISecondHandleSubscriber;
import com.zhenmei.p7i.mvpmamba.mvp.contract.UserContract;
import com.zhenmei.p7i.mvpmamba.mvp.entity.UserEntity;
import com.zhenmei.p7i.mvpmamba.mvp.model.api.subject.CommonListSubject;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class UserPresenter extends BasePresenter<UserContract.Model, UserContract.MView> {
    @Inject
    public UserPresenter(UserContract.Model model, UserContract.MView mView) {
        super(model, mView);
    }

    @Inject
    Context context;


    public void getUser() {

        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("qid", getId().toString());
        paramMap.put("page", String.valueOf(page));
        paramMap.put("rows", String.valueOf(rows));
        paramMap.put("sortType", String.valueOf(sortType));

        mModel.getUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mView))
                .subscribe(new P7IHandlerSubscriber<CommonListSubject<UserEntity>>(context) {
                    @Override
                    public void onNext(CommonListSubject<UserEntity> subject) {

                        Logger.i(subject.rows.size() + "");
                    }


                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        e.printStackTrace();
                    }
                });


    }


}
