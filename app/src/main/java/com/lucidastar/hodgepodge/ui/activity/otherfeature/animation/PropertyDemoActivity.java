package com.lucidastar.hodgepodge.ui.activity.otherfeature.animation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.lucidastar.hodgepodge.R;
import com.lucidastar.hodgepodge.ui.activity.otherfeature.animation.other.BaseEasingMethod;
import com.lucidastar.hodgepodge.ui.activity.otherfeature.animation.other.Glider;
import com.lucidastar.hodgepodge.ui.activity.otherfeature.animation.other.Skill;
import com.lucidastar.hodgepodge.ui.activity.otherfeature.animation.view.DrawView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PropertyDemoActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.history)
    DrawView mHistory;
    @BindView(R.id.target)
    View mTarget;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_demo);
        ButterKnife.bind(this);
        initData();
        initListener();
    }

    private void initListener() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void openAnim(View view){
        mHistory.clear();
        Skill s = (Skill) view.getTag();
        AnimatorSet set = new AnimatorSet();
        mTarget.setTranslationX(0);
        mTarget.setTranslationY(0);
        set.playTogether(
                Glider.glide(Skill.BounceEaseIn, 1200, ObjectAnimator.ofFloat(mTarget, "translationY", 0, dipToPixels(PropertyDemoActivity.this, -(160 - 3))), new BaseEasingMethod.EasingListener() {
                    @Override
                    public void on(float time, float value, float start, float end, float duration) {
                        mHistory.drawPoint(time, duration, value - dipToPixels(PropertyDemoActivity.this, 60));
                    }
                })
        );
        set.setDuration(1200);
        set.start();
    }
    public static float dipToPixels(Context context, float dipValue) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, metrics);
    }

    private void initData() {

        toolbar.setTitle("属性动画例子");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setOverflowIcon(getResources().getDrawable(R.drawable.ic_menu_more_overflow));


    }
}
