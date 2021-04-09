package com.zhenmei.mvpmamba.activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.trello.rxlifecycle4.android.ActivityEvent;
import com.zhenmei.mvpmamba.integration.lifecycle.ActivityLifeCycleAble;

import io.reactivex.rxjava3.subjects.BehaviorSubject;
import io.reactivex.rxjava3.subjects.Subject;


public class LifecycleActivity extends AppCompatActivity implements ActivityLifeCycleAble {
    private final BehaviorSubject<ActivityEvent> mLifecycleSubject = BehaviorSubject.create();

    @NonNull
    @Override
    public Subject<ActivityEvent> provideLifecycleSubject() {
        return mLifecycleSubject;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }


}
