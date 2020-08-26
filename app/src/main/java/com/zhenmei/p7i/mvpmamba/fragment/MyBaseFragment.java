package com.zhenmei.p7i.mvpmamba.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.zhenmei.p7i.core.fragment.FMVPFragment;
import com.zhenmei.p7i.core.mvp.BasePresenter;

import javax.inject.Inject;

public abstract class MyBaseFragment<P extends BasePresenter> extends FMVPFragment {
    @Inject
    protected P mPresenter;


    @Override
    protected boolean enableInject() {
        return true;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mPresenter.attachView(this);
        mPresenter.setContext(getContext());
        super.onViewCreated(view, savedInstanceState);
    }
}
