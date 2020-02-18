package com.zhenmei.p7i.core.bottomsheet;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.zhenmei.p7i.core.R;

public class BottomSheetDialogFragment extends AppCompatDialogFragment {
    /**
     * Tracks if we are waiting for a dismissAllowingStateLoss or a regular dismiss once the
     * BottomSheet is hidden and onStateChanged() is called.
     */
    private boolean waitingForDismissAllowingStateLoss;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new BottomSheetDialog(getContext(), getTheme());
    }

    @Override
    public void dismiss() {
        if (!tryDismissWithAnimation(false)) {
            super.dismiss();
        }
    }

    @Override
    public void dismissAllowingStateLoss() {
        if (!tryDismissWithAnimation(true)) {
            super.dismissAllowingStateLoss();
        }
    }

    @NonNull
    @Override
    public BottomSheetDialog getDialog() {
        return (BottomSheetDialog) super.getDialog();
    }

    /**
     * Tries to dismiss the dialog fragment with the bottom sheet animation. Returns true if possible,
     * false otherwise.
     */
    private boolean tryDismissWithAnimation(boolean allowingStateLoss) {
        ViewPagerBottomSheetBehavior<View> behavior = getBottomSheetBehavior();
        if (behavior != null && behavior.isHideable() && getDialog().getDismissWithAnimation()) {
            dismissWithAnimation(behavior, allowingStateLoss);
            return true;
        }

        return false;
    }

    @Nullable
    private ViewPagerBottomSheetBehavior<View> getBottomSheetBehavior() {
        Dialog dialog = getDialog();
        if (dialog != null) {
            View bottomSheet = dialog.findViewById(R.id.design_bottom_sheet);
            if (bottomSheet != null) {
                return ViewPagerBottomSheetBehavior.from(bottomSheet);
            }
        }
        return null;
    }

    private void dismissWithAnimation(
            @NonNull ViewPagerBottomSheetBehavior<View> behavior, boolean allowingStateLoss) {
        waitingForDismissAllowingStateLoss = allowingStateLoss;

        if (behavior.getState() == ViewPagerBottomSheetBehavior.STATE_HIDDEN) {
            dismissAfterAnimation();
        } else {
            // Wrap the old callback in a BottomSheetDismissCallback to pass along the state change.
            if (!(behavior.getBottomSheetCallback() instanceof BottomSheetDismissCallback)) {
                BottomSheetDialogFragment.BottomSheetDismissCallback dismissCallback =
                        new BottomSheetDialogFragment.BottomSheetDismissCallback(behavior.getBottomSheetCallback());
                behavior.setBottomSheetCallback(dismissCallback);
            }

            behavior.setState(ViewPagerBottomSheetBehavior.STATE_HIDDEN);
        }
    }

    private void dismissAfterAnimation() {
        if (waitingForDismissAllowingStateLoss) {
            super.dismissAllowingStateLoss();
        } else {
            super.dismiss();
        }
    }

    private class BottomSheetDismissCallback extends ViewPagerBottomSheetBehavior.BottomSheetCallback {

        private final ViewPagerBottomSheetBehavior.BottomSheetCallback originalCallback;

        public BottomSheetDismissCallback(ViewPagerBottomSheetBehavior.BottomSheetCallback originalCallback) {
            this.originalCallback = originalCallback;
        }

        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
            if (newState == ViewPagerBottomSheetBehavior.STATE_HIDDEN) {
                dismissAfterAnimation();
            }

            originalCallback.onStateChanged(bottomSheet, newState);
        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            originalCallback.onSlide(bottomSheet, slideOffset);
        }
    }
}
