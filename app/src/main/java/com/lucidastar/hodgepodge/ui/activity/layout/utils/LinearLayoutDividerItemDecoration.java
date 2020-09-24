package com.lucidastar.hodgepodge.ui.activity.layout.utils;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author hyli
 * description：
 * @date 2020/9/7 11:26
 */
public class LinearLayoutDividerItemDecoration extends RecyclerView.ItemDecoration {
    private float mDividerHeight;

    private Paint mPaint;

    public LinearLayoutDividerItemDecoration() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        int childAdapterPosition = parent.getChildAdapterPosition(view);
        if (childAdapterPosition != 0){
            outRect.top = 3;
            mDividerHeight = 3;
        }

    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {

            View view = parent.getChildAt(i);
            int index = parent.getChildAdapterPosition(view);

            float top = view.getTop() - mDividerHeight;
            float left = parent.getPaddingLeft();
            float bottom = view.getTop();
            float right = parent.getWidth() - parent.getPaddingRight();

            c.drawRect(left,top,right,bottom,mPaint);
        }

    }
}
