package com.lucidastar.hodgepodge.ui.activity.otherfeature.animation.other;

import com.lucidastar.hodgepodge.ui.activity.otherfeature.animation.other.bounce.BounceEaseIn;
import com.lucidastar.hodgepodge.ui.activity.otherfeature.animation.other.bounce.BounceEaseOut;
import com.lucidastar.hodgepodge.ui.activity.otherfeature.animation.other.bounce.BounceEaseInOut;

/**
 * Created by qiuyouzone on 2018/1/16.
 */

public enum  Skill {
    BounceEaseIn(BounceEaseIn.class),
    BounceEaseOut(BounceEaseOut.class),
    BounceEaseInOut(BounceEaseInOut.class);

    private Class easingMethod;

    private Skill(Class clazz) {
        easingMethod = clazz;
    }

    public BaseEasingMethod getMethod(float duration) {
        try {
            return (BaseEasingMethod)easingMethod.getConstructor(float.class).newInstance(duration);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Error("Can not init easingMethod instance");
        }
    }
}
