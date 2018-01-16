package com.lucidastar.hodgepodge.ui.activity.otherfeature.animation.other.bounce;

import com.lucidastar.hodgepodge.ui.activity.otherfeature.animation.other.BaseEasingMethod;

/**
 * Created by qiuyouzone on 2018/1/16.
 */

public class BounceEaseOut extends BaseEasingMethod {
    public BounceEaseOut(float duration) {
        super(duration);
    }

    @Override
    public Float calculate(float t, float b, float c, float d) {
        if ((t/=d) < (1/2.75f)) {
            return c*(7.5625f*t*t) + b;
        } else if (t < (2/2.75f)) {
            return c*(7.5625f*(t-=(1.5f/2.75f))*t + .75f) + b;
        } else if (t < (2.5/2.75)) {
            return c*(7.5625f*(t-=(2.25f/2.75f))*t + .9375f) + b;
        } else {
            return c*(7.5625f*(t-=(2.625f/2.75f))*t + .984375f) + b;
        }
    }
}
