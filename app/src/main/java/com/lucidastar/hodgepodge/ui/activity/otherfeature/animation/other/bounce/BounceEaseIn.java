package com.lucidastar.hodgepodge.ui.activity.otherfeature.animation.other.bounce;

import com.lucidastar.hodgepodge.ui.activity.otherfeature.animation.other.BaseEasingMethod;

/**
 * Created by qiuyouzone on 2018/1/16.
 */

public class BounceEaseIn extends BaseEasingMethod {
    private BounceEaseOut mBounceEaseOut;
    public BounceEaseIn(float duration) {
        super(duration);
        mBounceEaseOut = new BounceEaseOut(duration);
    }

    @Override
    public Float calculate(float t, float b, float c, float d) {
        return c - mBounceEaseOut.calculate(d-t, 0, c, d) + b;
    }
}
