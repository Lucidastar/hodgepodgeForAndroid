package com.lucidastar.hodgepodge.view;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TagLayout extends ViewGroup {
    private List<Rect> mChildBounds = new ArrayList<>();

    public TagLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {



        int childHeightUsed = 0;//单个子view的高
        int lineUseHeight = 0;//已经使用的高度
        int lineUseWidth = 0;//单行使用的宽度

        int maxWidth = 0;
        int maxHeight = 0;

        int specWidth = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int lineNum = 1;
        Log.i("mine", "child的数量：" + getChildCount());
        for (int i = 0; i < getChildCount(); i++) {//一个一个子view进行测量
            View childView = getChildAt(i);
            measureChildWithMargins(childView, widthMeasureSpec, 0, heightMeasureSpec, 0);
            if (widthMode != MeasureSpec.UNSPECIFIED && childView.getMeasuredWidth() + lineUseWidth > specWidth) {//宽度已经不够了，需要换行  把高度增加
                lineUseWidth = 0;

                childHeightUsed += lineUseHeight;
                lineUseHeight = 0;
                lineNum++;
                Log.i("mine", "child使用的高度：" + childHeightUsed);
//                measureChildWithMargins(childView, widthMeasureSpec, 0, heightMeasureSpec, 0);
            }

            Rect childBounds = null;
            if (mChildBounds.size() <= i) {
                childBounds = new Rect();
                mChildBounds.add(childBounds);
            } else {
                childBounds = mChildBounds.get(i);
            }
            childBounds.set(lineUseWidth, childHeightUsed, lineUseWidth + childView.getMeasuredWidth(), childHeightUsed + childView.getMeasuredHeight());

            lineUseWidth += childView.getMeasuredWidth();
            maxWidth = Math.max(maxWidth, lineUseWidth);

            lineUseHeight = Math.max(lineUseHeight, childView.getMeasuredHeight());

        }

        int width = maxWidth;
        int height = lineUseHeight + childHeightUsed;
        Log.i("mine", "child测量出来的高度：" + height);
        setMeasuredDimension(width, height+600);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            Rect rect = mChildBounds.get(i);
            childAt.layout(rect.left, rect.top, rect.right, rect.bottom);
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }
}
