package com.lucidastar.hodgepodge.ui.activity.otherfeature.animation.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;

/**
 * Created by qiuyouzone on 2018/1/16.
 */

public class DrawView extends View {

    private Paint mBackgroundPaint = new Paint();
    private Paint mLinePaint = new Paint();
    private Path path = new Path();
    private boolean start = false;

    private ArrayList<Float> mHistory = new ArrayList<Float>();

    {
        mBackgroundPaint.setColor(Color.WHITE);
        mLinePaint.setColor(Color.rgb(77,83,96));
        mLinePaint.setAntiAlias(true);
        mLinePaint.setStrokeWidth((float) 3.0);
        mLinePaint.setStyle(Paint.Style.STROKE);
    }

    public DrawView(Context context) {
        super(context);
    }

    public DrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float l = 0;
        float t = getHeight() - getPaddingBottom() - dipToPixels(getContext(),217);
        float r = getWidth() - getPaddingRight();
        float b = getHeight() - dipToPixels(getContext(),60);
        canvas.drawRect(l,t,r,b,mLinePaint);
        canvas.drawPath(path,mLinePaint);
    }

    public static float dipToPixels(Context context, float dipValue) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, metrics);
    }

    public void drawPoint(float time, float duration, float y){
        float p = time/duration;
        float x = p*getWidth();
        float z = getHeight() + y;
        if(!start){
            path.moveTo(x,z);
            start = true;
        }
        path.lineTo(x,z);
        invalidate();
    }

    public void clear(){
        path.reset();
        start = false;
        invalidate();
    }
}
