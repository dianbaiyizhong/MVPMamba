package com.zhenmei.mvpmamba.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.blankj.utilcode.util.ActivityUtils;
import com.zhenmei.mvpmamba.demo.mvp.view.WeatherActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityUtils.startActivity(WeatherActivity.class);
//        FragmentUtils.add(getSupportFragmentManager(), new UserFragment(), R.id.container);

    }
}