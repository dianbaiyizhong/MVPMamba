package com.zhenmei.mvpmamba.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.zhenmei.mvpmamba.mvp.BasePresenter;

public abstract class BaseMVPActivity<P extends BasePresenter> extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

}
