package com.lucidastar.hodgepodge.ui.activity.otherfeature.animation.view;

import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.lucidastar.hodgepodge.ui.activity.otherfeature.animation.ColorEvaluator;
import com.lucidastar.hodgepodge.ui.activity.otherfeature.animation.PointEvaluator;
import com.lucidastar.hodgepodge.ui.activity.otherfeature.animation.model.Point;

/**
 * Created by qiuyouzone on 2018/1/12.
 */

public class MyAnimView extends View {

    public static final float RADIUS = 50f;

    private Point currentPoint;

    private Paint mPaint;
    private String color;
    private int alpha;
    public MyAnimView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (currentPoint == null) {
            currentPoint = new Point(RADIUS, RADIUS);
            drawCircle(canvas);
            startAnimation();
        } else {
            drawCircle(canvas);
        }
    }
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
        mPaint.setColor(Color.parseColor(color));
//        invalidate();
    }



    private void drawCircle(Canvas canvas) {
        float x = currentPoint.getX();
        float y = currentPoint.getY();
        canvas.drawCircle(x, y, RADIUS, mPaint);
    }

    private void startAnimation() {
        Point startPoint = new Point(RADIUS, RADIUS);
        Point endPoint = new Point(getWidth() - RADIUS, getHeight() - RADIUS);
        ValueAnimator anim = ValueAnimator.ofObject(new PointEvaluator(), startPoint, endPoint);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentPoint = (Point) animation.getAnimatedValue();
                invalidate();
            }
        });

        ObjectAnimator animator = ObjectAnimator.ofObject(this,"color",new ColorEvaluator(),
                "#0000FF","#FF0000");
        ArgbEvaluator argbEvaluator = new ArgbEvaluator();

//        color = (String)argbEvaluator.evaluate(23.43f,"#0000FF","#FF0000");
//        ObjectAnimator animator = ObjectAnimator.ofObject(this,"color",argbEvaluator,
//                "#0000FF","#FF0000");

//        anim.setDuration(5000);
//        anim.start();
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(anim).with(animator);
        animSet.setDuration(10000);
        animSet.start();


    }

}
