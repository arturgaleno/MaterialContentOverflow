package com.materialoverflowcontent;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.view.ViewHelper;

import java.lang.ref.WeakReference;

/**
 * Created by artur on 16/08/15.
 */
public class OverflowGestureListener extends GestureDetector.SimpleOnGestureListener {

    private Boolean ifScrollUpping;

    private float initialYPosition = -1;

    private WeakReference<ViewGroup> overflow;

    private GestureDetectorCompat gestureDetectorCompat;

    public OverflowGestureListener(ViewGroup overflow) {
        this.overflow = new WeakReference<>(overflow);
        this.gestureDetectorCompat = new GestureDetectorCompat(
                overflow.getContext(), this);
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float distanceX, float distanceY) {
        if (motionEvent2.getRawY() <= initialYPosition) {
            ViewHelper.setTranslationY(overflow.get(), motionEvent2.getRawY());
            if (motionEvent.getRawY() < motionEvent2.getRawY()) {
                ifScrollUpping = false;
            } else {
                ifScrollUpping = true;
            }
        }
        return true;
    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float velocityX, float velocityY) {
        ifScrollUpping = null;
        if (motionEvent.getRawY() < motionEvent2.getRawY()) {
            slide(initialYPosition);
        } else {
            slide(0f);
        }
        return true;
    }

    public void slide(float position) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(overflow.get(), "translationY", position);
        objectAnimator.setInterpolator(new LinearOutSlowInInterpolator());
        objectAnimator.setDuration(1000);
        objectAnimator.start();
    }

    View.OnTouchListener motionEvent = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            gestureDetectorCompat.onTouchEvent(motionEvent);
            view.onTouchEvent(motionEvent);
            if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                if (ifScrollUpping != null) {
                    if (ifScrollUpping) {
                        slide(0f);
                    } else {
                        slide(initialYPosition);
                    }
                }
            }
            return true;
        }
    };

    public View.OnTouchListener getMotionEvent() {
        return motionEvent;
    }

    public void setInitialYPosition(float initialYPosition) {
        this.initialYPosition = initialYPosition;
    }

    public void clearReferences() {
        overflow.clear();
        overflow = null;
        gestureDetectorCompat = null;
    }
}
