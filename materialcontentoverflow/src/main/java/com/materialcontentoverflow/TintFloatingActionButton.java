package com.materialcontentoverflow;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.TintableBackgroundView;
import android.support.v7.internal.widget.TintInfo;
import android.support.v7.internal.widget.TintManager;
import android.support.v7.internal.widget.TintTypedArray;
import android.util.AttributeSet;

/**
 * Created by arturgaleno on 26/08/15.
 */
public class TintFloatingActionButton extends FloatingActionButton implements TintableBackgroundView {

    static final int[] PRESSED_ENABLED_STATE_SET = {android.R.attr.state_pressed,
            android.R.attr.state_enabled};
    static final int[] FOCUSED_ENABLED_STATE_SET = {android.R.attr.state_focused,
            android.R.attr.state_enabled};

    private static final int[] TINT_ATTRS = {
            android.R.attr.background
    };

    private TintInfo mBackgroundTint;

    public TintFloatingActionButton(Context context) {
        super(context);
    }

    public TintFloatingActionButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TintFloatingActionButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        if (TintManager.SHOULD_BE_USED) {
            TintTypedArray a = TintTypedArray.obtainStyledAttributes(getContext(), attrs,
                    TINT_ATTRS, defStyleAttr, 0);

            if (a.hasValue(0)) {
                setSupportBackgroundTintList(createColorStateList(a.getResourceId(0, -1)));
            }

            a.recycle();
        }
    }

    private static ColorStateList createColorStateList(int selectedColor) {
        final int[][] states = new int[3][];
        final int[] colors = new int[3];
        int i = 0;

        states[i] = FOCUSED_ENABLED_STATE_SET;
        colors[i] = selectedColor;
        i++;

        states[i] = PRESSED_ENABLED_STATE_SET;
        colors[i] = selectedColor;
        i++;

        // Default enabled state
        states[i] = new int[0];
        colors[i] = Color.TRANSPARENT;
        i++;

        return new ColorStateList(states, colors);
    }

    @Override
    public void setSupportBackgroundTintList(ColorStateList tint) {
        if (mBackgroundTint == null) {
            mBackgroundTint = new TintInfo();
        }
        mBackgroundTint.mTintList = tint;
        mBackgroundTint.mHasTintList = tint != null;
        applySupportBackgroundTint();
    }

    @Nullable
    @Override
    public ColorStateList getSupportBackgroundTintList() {
        return mBackgroundTint != null ? mBackgroundTint.mTintList : null;
    }

    @Override
    public void setSupportBackgroundTintMode(@Nullable PorterDuff.Mode tintMode) {
        if (mBackgroundTint == null) {
            mBackgroundTint = new TintInfo();
        }
        mBackgroundTint.mTintMode = tintMode;
        mBackgroundTint.mHasTintMode = tintMode != null;
        applySupportBackgroundTint();
    }

    @Nullable
    @Override
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        return mBackgroundTint != null ? mBackgroundTint.mTintMode : null;
    }

    private void applySupportBackgroundTint() {
        if (getBackground() != null && mBackgroundTint != null) {
            TintManager.tintViewBackground(this, mBackgroundTint);
        }
    }
}
