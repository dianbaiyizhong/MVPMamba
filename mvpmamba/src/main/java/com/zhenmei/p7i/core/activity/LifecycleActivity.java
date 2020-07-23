package com.zhenmei.p7i.core.activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.trello.rxlifecycle2.android.ActivityEvent;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;

public class LifecycleActivity extends AppCompatActivity {
//    private final BehaviorSubject<ActivityEvent> mLifecycleSubject = BehaviorSubject.create();
//
//    @NonNull
//    @Override
//    public Subject<ActivityEvent> provideLifecycleSubject() {
//        return mLifecycleSubject;
//    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }


}
