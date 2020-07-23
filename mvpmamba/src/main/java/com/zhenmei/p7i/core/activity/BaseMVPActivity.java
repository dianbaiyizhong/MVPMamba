package com.zhenmei.p7i.core.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.zhenmei.p7i.core.mvp.BasePresenter;
import com.zhenmei.p7i.core.mvp.IView;

public abstract class BaseMVPActivity<P extends BasePresenter> extends AppCompatActivity implements IView {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

}
