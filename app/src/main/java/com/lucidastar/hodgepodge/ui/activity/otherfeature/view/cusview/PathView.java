package com.lucidastar.hodgepodge.ui.activity.otherfeature.view.cusview;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.mine.lucidastarutils.utils.ScreenUtils;

public class PathView extends View {
    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Path mPath = new Path();
    Context mContext;
    private float mScaleVal;
    private float mRadius = 400;
    private static final String TAG = "mine";
    private float centerX;
    private float centerY;
    private float angle;
    private float mScale = 0;

    Paint mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private float mCircleRadius = 12f;
    public PathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
//        startCheckedAnimation();
    }
    void init(){
        mPaint.setColor(Color.parseColor("#ff0000"));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(ScreenUtils.px2dp(mContext,26));
        centerX = 600;
        centerY = 600;
        angle = (float) (Math.PI * 2 / 5);

        mCirclePaint.setColor(Color.parseColor("#aaffff"));
        mCirclePaint.setStyle(Paint.Style.FILL);
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setBackgroundColor(Color.parseColor("#ccffcc"));
//        mPath.addArc(200,100,600,500,-90,180);//画了一个180度的半圆
//        mPath.addRect(200,100,600,500, Path.Direction.CW);//画了一个正方形
//        mPath.addRect(200,100,600,500, Path.Direction.CCW);//画了一个正方形

//        mPath.addCircle(200,400,120, Path.Direction.CW);//画了一个圆

//        mPath.addOval(200,100,600,500,Path.Direction.CW);//又是一个圆  因为长宽都相等
//        mPath.addOval(200,100,500,300,Path.Direction.CW);//椭圆
//        mPath.addRoundRect(200,100,600,500,100,100, Path.Direction.CCW);//圆角正方形
//        mPath.addRoundRect(200,100,600,500,new float[]{20,60,120,120,20,20,10,160},Path.Direction.CCW);//不规则的矩形
//        mPath.moveTo(120,120);//从哪里开始
//        mPath.lineTo(120,280);//到这个位置  如果不调用moveTo，会默认从0,0开始
//        mPath.rMoveTo(0,2);//相对位置  相对于120,280的当前位置就是 120,282  也就是从120,282这个坐标点进行绘制
//        mPath.lineTo(300,700);
//
//        mPath.reset();//全部清空  从新开始绘制
//        mPath.moveTo(300,300);
//        mPath.lineTo(400,600);
//
//        mPath.rewind();
//        mPath.rMoveTo(100,20);
//        mPath.lineTo(500,1000);
//        mPath.offset(200,200);
//        mPath.lineTo(500,800);
        Path path = new Path();
        path.addCircle(300,300,mRadius, Path.Direction.CW);
//        mPath.set(path);//重新设置路径


//        mPath.moveTo(300,300);
//        mPath.lineTo(400,600);
//        mPath.addCircle(400,600,20, Path.Direction.CW);
//        mPath.moveTo(400,600);
//        mPath.lineTo(400,700);
//        mPath.addCircle(400,700,20, Path.Direction.CW);
//        mPath.moveTo(400,700);
//        mPath.lineTo(300,300);
//        mPath.close();
//        canvas.drawPath(mPath,mPaint);
        canvas.drawCircle(centerX,centerY,mRadius,mPaint);
        canvas.drawPath(mPath,mPaint);
        drawPoint(canvas,mScale);

    }

    public void setRadius(int radius) {
        mRadius = radius;
    }

    private void startCheckedAnimation() {
        ValueAnimator animator = ValueAnimator.ofFloat(20f, 240f);
//        ObjectAnimator animator = ObjectAnimator.ofFloat(this,"setRadius",20f,240f);
        animator.setDuration(2000);
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatCount(10);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mRadius = (float) animation.getAnimatedValue();
                mScale = (float) animation.getAnimatedValue();
                Log.i(TAG, "onAnimationUpdate: "+mScaleVal);
                postInvalidate();
            }
        });
        animator.start();

    }


    private void drawPoint(Canvas canvas, float scale) {


        Path path = new Path();
        int tx = 0,ty = 0;
        //先来个初始值
        int cx = 0,cy = 0;
        cx = (int) (centerX + 0.2 *  mRadius * Math.cos(angle * 0 + Math.PI / 2));
        cy = (int) (centerY - 0.2 *  mRadius * Math.sin(angle * 0 + Math.PI / 2));
        mPath.addCircle(cx,cy,mCircleRadius, Path.Direction.CW);
        mPath.moveTo(cx,cy);
        tx = cx;
        ty = cy;
        cx = (int) (centerX + 0.4 *  mRadius * Math.cos(angle * 1 + Math.PI / 2));
        cy = (int) (centerY - 0.4 *  mRadius * Math.sin(angle * 1 + Math.PI / 2));
        mPath.lineTo(cx,cy);
        mPath.addCircle(cx,cy,mCircleRadius, Path.Direction.CW);
        mPath.moveTo(cx,cy);

        cx = (int) (centerX + 0.24 *  mRadius * Math.cos(angle * 2 + Math.PI / 2));
        cy = (int) (centerY - 0.24 *  mRadius * Math.sin(angle * 2 + Math.PI / 2));
        mPath.lineTo(cx,cy);
        mPath.addCircle(cx,cy,mCircleRadius, Path.Direction.CW);
        mPath.moveTo(cx,cy);

        cx = (int) (centerX + 0.55 *  mRadius * Math.cos(angle * 3 + Math.PI / 2));
        cy = (int) (centerY - 0.55 *  mRadius * Math.sin(angle * 3 + Math.PI / 2));
        mPath.lineTo(cx,cy);
        mPath.addCircle(cx,cy,mCircleRadius, Path.Direction.CW);
        mPath.moveTo(cx,cy);
        cx = (int) (centerX + 0.6 *  mRadius * Math.cos(angle * 4 + Math.PI / 2));
        cy = (int) (centerY - 0.6 *  mRadius * Math.sin(angle * 4 + Math.PI / 2));
        mPath.lineTo(cx,cy);
        mPath.addCircle(cx,cy,mCircleRadius, Path.Direction.CW);
        mPath.moveTo(cx,cy);
        mPath.lineTo(tx,ty);

        mPath.close();
//        mPath.set(path);
//        mPath.close();
        canvas.drawPath(mPath,mPaint);
        for (int i = 0; i < 5; i++) {
            int x, y;
            x = (int) (centerX + scale *  mRadius * Math.cos(angle * i + Math.PI / 2));
            y = (int) (centerY - scale *  mRadius * Math.sin(angle * i + Math.PI / 2));
            canvas.drawLine(centerX, centerY, (float) (centerX + Math.cos(angle * i + Math.PI / 2) * mRadius), (float) (centerY - Math.sin(angle * i + Math.PI / 2) * mRadius), mPaint);

        }



    }

}
