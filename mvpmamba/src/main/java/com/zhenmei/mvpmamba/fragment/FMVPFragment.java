package com.zhenmei.mvpmamba.fragment;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.trello.rxlifecycle4.android.FragmentEvent;
import com.zhenmei.mvpmamba.di.AndroidInjection;
import com.zhenmei.mvpmamba.integration.lifecycle.FragmentLifecycleable;
import com.zhenmei.mvpmamba.mvp.IView;

import io.reactivex.rxjava3.subjects.BehaviorSubject;
import io.reactivex.rxjava3.subjects.Subject;


public abstract class FMVPFragment extends Fragment implements FragmentLifecycleable, IView {
    private final BehaviorSubject<FragmentEvent> mLifecycleSubject = BehaviorSubject.create();


    protected abstract boolean enableInject();


    @Override
    public void onAttach(@NonNull Context context) {
        if (enableInject()) {
            AndroidInjection.inject(this);
        }
        super.onAttach(context);
    }


    @NonNull
    @Override
    public Subject<FragmentEvent> provideLifecycleSubject() {
        return mLifecycleSubject;
    }
}
