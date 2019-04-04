package com.lucidastar.hodgepodge.view;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.mine.lucidastarutils.utils.ScreenUtils;


/**
 * @author by sunfusheng on 2017/6/27.
 */
public class DraggableFrameLayout extends FrameLayout {

    private ViewDragHelper dragHelper;
    private int screenWidth;
    private int margin = 10;
    private int finalLeft = -1;
    private int finalTop = -1;

    private static final String TAG = "DraggableFrameLayout";
    public DraggableFrameLayout(@NonNull Context context) {
        this(context, null);
    }

    public DraggableFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DraggableFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        screenWidth = ScreenUtils.getScreenWidth(getContext());
        margin = ScreenUtils.dp2px(getContext(), margin);

        dragHelper = ViewDragHelper.create(this, new ViewDragHelper.Callback() {

            @Override
            public boolean tryCaptureView(@NonNull View child, int pointerId) {
                return true;
            }

            @Override
            public void onViewCaptured(@NonNull View capturedChild, int activePointerId) {
                super.onViewCaptured(capturedChild, activePointerId);
            }

            @Override
            public int clampViewPositionHorizontal(@NonNull View child, int left, int dx) {
                return left;
            }

            @Override
            public int clampViewPositionVertical(@NonNull View child, int top, int dy) {
                return top;
            }

            @Override
            public int getViewHorizontalDragRange(@NonNull View child) {
                return getMeasuredWidth() - child.getMeasuredWidth();
            }

            @Override
            public int getViewVerticalDragRange(@NonNull View child) {
                return getMeasuredHeight() - child.getMeasuredHeight();
            }

            @Override
            public void onViewReleased(@NonNull View releasedChild, float xvel, float yvel) {
                super.onViewReleased(releasedChild, xvel, yvel);
                int viewWidth = releasedChild.getWidth();
                int viewHeight = releasedChild.getHeight();
                int curLeft = releasedChild.getLeft();
                int curTop = releasedChild.getTop();

                finalLeft = margin;
                finalTop = curTop < margin ? margin : curTop;

                if ((curLeft + viewWidth / 2) > screenWidth / 2) {
                    finalLeft = screenWidth - viewWidth - margin;
                }

                if ((finalTop + viewHeight) > getHeight()) {
                    finalTop = getHeight() - viewHeight - margin;
                }

                Log.i(TAG, "onViewReleased: finalLeft="+finalLeft+"-----finalTop="+finalTop+"-----margin="+margin);

//                finalTop = curTop < 0 ? 0 : curTop;
//                finalLeft = curLeft < 0 ? 0 : curLeft;
//                if ((finalTop + viewHeight) > getHeight()) {
//                    finalTop = getHeight() - viewHeight;
//                }
//
//                if ((finalLeft + viewWidth) > getWidth()) {
//                    finalLeft = getWidth() - viewWidth;
//                }

                dragHelper.settleCapturedViewAt(finalLeft, finalTop);
                invalidate();
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return dragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        dragHelper.processTouchEvent(event);
        return isTouchChildView(event);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (dragHelper.continueSettling(true)) {
            invalidate();
        }
    }

    private boolean isTouchChildView(MotionEvent ev) {
        Rect rect = new Rect();
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            rect.set((int) view.getX(), (int) view.getY(), (int) view.getX() + view.getWidth(), (int) view.getY() + view.getHeight());
            if (rect.contains((int) ev.getX(), (int) ev.getY())) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        if (finalLeft == -1 && finalTop == -1) {
            super.onLayout(changed, left, top, right, bottom);
        }
    }
}
