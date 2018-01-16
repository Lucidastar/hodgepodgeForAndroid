package com.lucidastar.hodgepodge.ui.activity.otherfeature.animation.other;

import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;

/**
 * Created by qiuyouzone on 2018/1/16.
 */

public class Glider {

    public static ValueAnimator glide(Skill skill, float duration, ValueAnimator animator) {
        return Glider.glide(skill, duration, animator, (BaseEasingMethod.EasingListener[]) null);
    }

    public static ValueAnimator glide(Skill skill, float duration, ValueAnimator animator, BaseEasingMethod.EasingListener... listeners) {
        BaseEasingMethod t = skill.getMethod(duration);

        if (listeners != null)
            t.addEasingListeners(listeners);

        animator.setEvaluator(t);
        return animator;
    }

    public static PropertyValuesHolder glide(Skill skill, float duration, PropertyValuesHolder propertyValuesHolder) {
        propertyValuesHolder.setEvaluator(skill.getMethod(duration));
        return propertyValuesHolder;
    }
}
