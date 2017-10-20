package com.lucidastar.hodgepodge.ui.activity.otherfeature.animation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.lucidastar.hodgepodge.R;
import com.mine.lucidastarutils.log.KLog;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PropertyAnimActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_property)
    TextView tvProperty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_anim);
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

    private void initData() {

        toolbar.setTitle("属性动画的学习");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setOnMenuItemClickListener(onMenuItemClick);
        toolbar.setOverflowIcon(getResources().getDrawable(R.drawable.ic_menu_more_overflow));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.property_anim_menu, menu);
        return true;
    }

    public void openButton1(View view) {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f, 1f);
        valueAnimator.setDuration(3000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                KLog.d("值的变化：" + animation.getAnimatedValue());
            }
        });
        valueAnimator.start();
    }

    private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            String msg = "";
            switch (menuItem.getItemId()) {
                case R.id.item_alpha:
                    msg += "alpha";
                    startAnimation(0);
                    break;
                case R.id.item_rotation:
                    msg += "rotation";
                    startAnimation(1);
                    break;
                case R.id.translationX:
                    msg += "translationX";
                    startAnimation(2);
                    break;
                case R.id.item_scaleY:
                    msg += "scaleY";
                    startAnimation(3);
                    break;
                case R.id.item_all:
                    msg += "all";
                    startAnimation(4);
                    break;
            }

            if (!msg.equals("")) {

            }
            return true;
        }
    };


    private void startAnimation(int pro) {
        ObjectAnimator animator = null;
        if (pro == 0) {
            animator = ObjectAnimator.ofFloat(tvProperty, "alpha", 1f, 3f, 1f);
        }else if (pro == 1){
            animator = ObjectAnimator.ofFloat(tvProperty, "rotation", 0f, 360f);
        }else if (pro == 2){
            float curTranslationX = tvProperty.getTranslationX();
            animator = ObjectAnimator.ofFloat(tvProperty, "translationX", curTranslationX, -500f, curTranslationX);
        }else if (pro == 3){
            animator = ObjectAnimator.ofFloat(tvProperty, "scaleY", 1f, 3f, 1f);
        }else if (pro == 4){
            ObjectAnimator moveIn = ObjectAnimator.ofFloat(tvProperty, "translationX", -500f, 0f);
            ObjectAnimator rotate = ObjectAnimator.ofFloat(tvProperty, "rotation", 0f, 360f);
            ObjectAnimator fadeInOut = ObjectAnimator.ofFloat(tvProperty, "alpha", 1f, 0f, 1f);
            AnimatorSet animSet = new AnimatorSet();
            animSet.play(rotate).with(fadeInOut).after(moveIn);
            animSet.setDuration(5000);
            animSet.start();
        }
        if (pro != 4) {
            animator.setDuration(5000);
            animator.start();
        }
    }
}
