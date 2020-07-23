package com.zhenmei.p7i.core.fragment;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.trello.rxlifecycle2.android.FragmentEvent;
import com.zhenmei.p7i.core.di.AndroidInjection;
import com.zhenmei.p7i.core.integration.lifecycle.FragmentLifecycleable;
import com.zhenmei.p7i.core.mvp.IView;

import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;

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
