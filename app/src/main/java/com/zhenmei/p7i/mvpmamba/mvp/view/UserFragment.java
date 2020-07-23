package com.zhenmei.p7i.mvpmamba.mvp.view;

import com.zhenmei.p7i.mvpmamba.fragment.MyFragment;
import com.zhenmei.p7i.mvpmamba.mvp.contract.UserContract;
import com.zhenmei.p7i.mvpmamba.mvp.presenter.UserPresenter;

public class UserFragment extends MyFragment<UserPresenter> implements UserContract.MView {
    @Override
    public void loadSuccess() {

    }

}
