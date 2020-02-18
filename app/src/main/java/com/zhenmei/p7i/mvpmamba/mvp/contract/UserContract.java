package com.zhenmei.p7i.mvpmamba.mvp.contract;

import com.zhenmei.p7i.core.mvp.IModel;
import com.zhenmei.p7i.core.mvp.IView;
import com.zhenmei.p7i.mvpmamba.mvp.entity.UserEntity;
import com.zhenmei.p7i.mvpmamba.mvp.model.api.subject.CommonListSubject;

import io.reactivex.Observable;

public interface UserContract {
    interface Model extends IModel {


        Observable<CommonListSubject<UserEntity>> getUser();

    }

    interface MView extends IView {


        void loadSuccess();


    }
}
