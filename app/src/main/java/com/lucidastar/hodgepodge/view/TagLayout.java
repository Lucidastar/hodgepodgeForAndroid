package com.lucidastar.hodgepodge.view;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class TagLayout extends ViewGroup {
    private List<Rect> mChildBounds = new ArrayList<>();
    public TagLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = 0;
        int height = 0;
        int childWidthUsed = 0;
        int childHeightUsed = 0;
        for (int i = 0; i < getChildCount(); i++) {

            int widthMode = MeasureSpec.getMode(widthMeasureSpec);
            View childView = getChildAt(i);
            measureChildWithMargins(childView,widthMeasureSpec,childWidthUsed,heightMeasureSpec,childHeightUsed);
        }

        setMeasuredDimension(width,height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            Rect rect = mChildBounds.get(i);
            childAt.layout(rect.left,rect.top,rect.right,rect.bottom);
        }
    }
}
