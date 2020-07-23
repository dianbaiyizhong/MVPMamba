package com.zhenmei.p7i.core.fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.trello.rxlifecycle2.android.FragmentEvent;
import com.zhenmei.p7i.core.integration.lifecycle.FragmentLifecycleable;

import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;

public class FMVPFragment extends Fragment implements FragmentLifecycleable {
    private final BehaviorSubject<FragmentEvent> mLifecycleSubject = BehaviorSubject.create();

    @NonNull
    @Override
    public Subject<FragmentEvent> provideLifecycleSubject() {
        return mLifecycleSubject;
    }
}
