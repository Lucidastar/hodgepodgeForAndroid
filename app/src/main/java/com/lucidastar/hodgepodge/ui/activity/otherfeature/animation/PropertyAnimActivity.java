package com.lucidastar.hodgepodge.ui.activity.otherfeature.animation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.widget.TextView;

import com.lucidastar.hodgepodge.R;
import com.mine.lucidastarutils.log.KLog;
import com.mine.lucidastarutils.log.KLogUtil;
import com.mine.lucidastarutils.utils.*;
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

    public void openButton2(View view) {
        startActivity(new Intent(this,PropertyDemoActivity.class));

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void openButton1(View view) {
        int[] location = new int[2];
        tvProperty.getLocationOnScreen(location);
        int x = location[0];
        int y = location[1];
        KLog.d("x、y的值：" + x+","+y);

        tvProperty.getLocationInWindow(location);
        int x1 = location[0];
        int y1 = location[1];
        KLog.d("x1、y1的值：" + x1+","+y1);


        float translationX = tvProperty.getTranslationX();
        KLog.d("translationX的值：" + translationX);
        KLog.d("left、top的值：" +tvProperty.getLeft()+","+tvProperty.getTop());
        int statusHeight = ScreenUtils.getStatusHeight(this);
        KLog.d("statusHeight的值：" + statusHeight);

//        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f, 1f);
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 300);
        valueAnimator.setDuration(3000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                KLog.d("值的变化：" + animation.getAnimatedValue());
                int animatedValue = (int)animation.getAnimatedValue();
                tvProperty.layout(tvProperty.getLeft(),animatedValue,animatedValue+tvProperty.getWidth(),animatedValue+tvProperty.getHeight());
//                KLog.d("left、top的值：" +tvProperty.getLeft()+","+tvProperty.getTop());
//                KLog.d("width、height的值：" +tvProperty.getWidth()+","+tvProperty.getHeight());
            }
        });
        valueAnimator.start();

//        ValueAnimator.ofObject()


        testTimeAnim();
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
                case R.id.item_xml:
                    startAnimation(5);
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
//            ObjectAnimator.ofInt(tvProperty, "alpha", 1, 3, 1);
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
        }else if (pro == 5){
            Animator animator1 = AnimatorInflater.loadAnimator(PropertyAnimActivity.this, R.animator.property_test);
            animator1.setTarget(tvProperty);
            animator1.start();
        }
        if (pro != 4 && pro != 5) {
            animator.setDuration(5000);
            animator.start();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void testTimeAnim(){
        final TimeAnimator timeAnimator = new TimeAnimator();
        timeAnimator.setTimeListener(new TimeAnimator.TimeListener() {
            @Override
            public void onTimeUpdate(TimeAnimator animation, long totalTime, long deltaTime) {
                KLog.i("totalTime:"+totalTime+",deltaTime:"+deltaTime);
            }
        });
        timeAnimator.start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                timeAnimator.cancel();
            }
        },4000);

        PackageManager packageManager = getPackageManager();
    }
}
