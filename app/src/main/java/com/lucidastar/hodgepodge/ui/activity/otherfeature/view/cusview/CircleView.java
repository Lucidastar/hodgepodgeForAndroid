package com.lucidastar.hodgepodge.ui.activity.otherfeature.view.cusview;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.provider.Settings;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.RequiresApi;

import com.mine.lucidastarutils.utils.ScreenUtils;
import com.mine.lucidastarutils.utils.Utils;

public class CircleView extends View implements Runnable{

    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final static int RADIUS = (int) dp2px(120);
    Paint.FontMetrics fontMetrics = new Paint.FontMetrics();
    private int mProgress = 0;
    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        new Thread(this).start();
    }

    {
        mPaint.setColor(Color.parseColor("#436EEE"));
        mPaint.setStrokeWidth(40);
        mPaint.setStyle(Paint.Style.STROKE);

        mPaint.setTextSize(dp2px(60));
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.getFontMetrics(fontMetrics);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        canvas.drawCircle(getWidth() / 2,getHeight() / 2,RADIUS,mPaint);
//        mPaint.setColor(Color.parseColor("#FF00FF"));
//        mPaint.setStrokeCap(Paint.Cap.ROUND);
//        canvas.drawArc(getWidth() / 2 - RADIUS,getHeight() / 2 -RADIUS,getWidth() / 2 + RADIUS,getHeight() / 2 +RADIUS,-90,200,false,mPaint);
//
//        mPaint.setStyle(Paint.Style.FILL);
//
//        float offset = (fontMetrics.ascent + fontMetrics.descent) / 2;//解决字体不居中的问题
//        canvas.drawText("aaaaaa",getWidth() / 2,getHeight() / 2 - offset,mPaint);

        drawCircle(canvas,mProgress);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void drawCircle(Canvas canvas, int progress){
        canvas.drawArc(getWidth() / 2 - RADIUS,getHeight() / 2 -RADIUS,getWidth() / 2 + RADIUS,getHeight() / 2 +RADIUS,-90,progress,false,mPaint);
    }

    public static float dp2px(float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(50);
                mProgress ++;
                if (mProgress > 360){
                    mProgress = 0;
                }
                postInvalidate();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
