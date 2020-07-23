package com.zhenmei.p7i.mvpmamba;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.FragmentUtils;
import com.zhenmei.p7i.mvpmamba.mvp.view.UserActivity;
import com.zhenmei.p7i.mvpmamba.mvp.view.UserFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ActivityUtils.startActivity(UserActivity.class);
        FragmentUtils.add(getSupportFragmentManager(), new UserFragment(), R.id.container);

    }
}
