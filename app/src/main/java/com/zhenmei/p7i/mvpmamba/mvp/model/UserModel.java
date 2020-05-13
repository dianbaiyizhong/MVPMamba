package com.zhenmei.p7i.mvpmamba.mvp.model;

import com.zhenmei.p7i.core.mvp.BaseModel;
import com.zhenmei.p7i.mvpmamba.net.PayLoad;
import com.zhenmei.p7i.mvpmamba.mvp.contract.UserContract;
import com.zhenmei.p7i.mvpmamba.mvp.entity.UserEntity;
import com.zhenmei.p7i.mvpmamba.mvp.model.api.service.UserService;
import com.zhenmei.p7i.mvpmamba.mvp.model.api.subject.CommonListSubject;

import io.reactivex.Observable;

public class UserModel extends BaseModel implements UserContract.Model {

    @Override
    public Observable<CommonListSubject<UserEntity>> getUser() {


        return observe(apiService(UserService.class).getList(bean2Map(null))).map(new PayLoad<>());
    }
}
