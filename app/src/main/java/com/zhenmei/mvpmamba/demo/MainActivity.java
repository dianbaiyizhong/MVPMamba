package com.zhenmei.mvpmamba.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.FragmentUtils;
import com.google.android.material.button.MaterialButton;
import com.orhanobut.logger.Logger;
import com.zhenmei.mvpmamba.demo.mvp.view.WeatherActivity;
import com.zhenmei.mvpmamba.demo.mvp.view.WeatherFragment;

public class MainActivity extends AppCompatActivity {

    private MaterialButton btnActivity;
    private MaterialButton btnFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnActivity = findViewById(R.id.btn_activity);

        btnFragment = findViewById(R.id.btn_fragment);
        btnActivity.setOnClickListener(view -> {
            ActivityUtils.startActivity(WeatherActivity.class);

        });
        btnFragment.setOnClickListener(view -> {
            WeatherFragment fragment = new WeatherFragment();
            FragmentUtils.add(getSupportFragmentManager(), fragment, R.id.container);
        });


    }
}