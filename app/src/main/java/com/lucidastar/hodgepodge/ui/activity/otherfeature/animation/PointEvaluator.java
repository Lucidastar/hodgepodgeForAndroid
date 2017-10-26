package com.lucidastar.hodgepodge.ui.activity.otherfeature.animation;

import android.animation.TypeEvaluator;

import com.lucidastar.hodgepodge.ui.activity.otherfeature.animation.model.Point;

/**
 * Created by qiuyouzone on 2017/10/20.
 */

public class PointEvaluator implements  TypeEvaluator{

    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        Point startPoint = (Point) startValue;
        Point endPoint = (Point) endValue;
        float x = startPoint.getX() + fraction * (endPoint.getX() - startPoint.getX());
        float y = startPoint.getY() + fraction * (endPoint.getY() - startPoint.getY());
        Point point = new Point(x, y);
        return point;
    }
}
