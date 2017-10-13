package com.lucidastar.hodgepodge.ui.activity.otherfeature.animation;

import android.animation.Animator;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSeekBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.lucidastar.hodgepodge.R;
import com.mine.lucidastarutils.utils.FileUtils;
import com.mine.lucidastarutils.utils.ImageUtils;
import com.mine.lucidastarutils.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AnimationActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.iv_test)
    ImageView ivTest;
    @BindView(R.id.tv_imageInfo)
    TextView tvImageInfo;
    @BindView(R.id.cb_change)
    CheckBox cbChange;
    @BindView(R.id.sb_scrollX)
    AppCompatSeekBar sbScrollX;
    @BindView(R.id.sb_scrollY)
    AppCompatSeekBar sbScrollY;
    @BindView(R.id.sb_translationX)
    AppCompatSeekBar sbTranslationX;
    @BindView(R.id.sb_translationY)
    AppCompatSeekBar sbTranslationY;
    @BindView(R.id.sb_rotationX)
    AppCompatSeekBar sbRotationX;
    @BindView(R.id.sb_rotationY)
    AppCompatSeekBar sbRotationY;
    @BindView(R.id.sb_scaleX)
    AppCompatSeekBar sbScaleX;
    @BindView(R.id.sb_scaleY)
    AppCompatSeekBar sbScaleY;
    @BindView(R.id.sb_pivotX)
    AppCompatSeekBar sbPivotX;
    @BindView(R.id.sb_pivotY)
    AppCompatSeekBar sbPivotY;

    private boolean isCheck;
    int changeProgress = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        ButterKnife.bind(this);
        initData();
        initListener();
    }

    private void initData() {
        toolbar.setTitle("动画");
        toolbar.setTitle("动画的学习");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        //获取图片信息
        tvImageInfo.setText("图片的大小:" + FileUtils.getBytesFormat(ImageUtils.getByteCount(((BitmapDrawable) ivTest.getDrawable()).getBitmap())));
    }

    private void initListener() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        cbChange.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isCheck = isChecked;
                if (isChecked) {
                    cbChange.setText("正向");
                } else {
                    cbChange.setText("反向");
                }
            }
        });

        ivTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showShortToast("单击了");
            }
        });

        sbScrollX.setOnSeekBarChangeListener(this);
        sbScrollY.setOnSeekBarChangeListener(this);
        sbRotationX.setOnSeekBarChangeListener(this);
        sbRotationY.setOnSeekBarChangeListener(this);
        sbScaleX.setOnSeekBarChangeListener(this);
        sbScaleY.setOnSeekBarChangeListener(this);
        sbTranslationX.setOnSeekBarChangeListener(this);
        sbTranslationY.setOnSeekBarChangeListener(this);
        sbPivotX.setOnSeekBarChangeListener(this);
        sbPivotY.setOnSeekBarChangeListener(this);
    }

    public void openPropertyAnim(View view){
        startActivity(new Intent(this,PropertyAnimActivity.class));
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()) {
            case R.id.sb_scrollX:
                ivTest.setScrollX(isCheck == true ? (progress * 2) : -(progress * 2));
                break;
            case R.id.sb_scrollY:
                ivTest.setScrollY(isCheck == true ? (progress * 2) : -(progress * 2));
                break;
            case R.id.sb_translationX:
                ivTest.setTranslationX(isCheck == true ? (progress * 2) : -(progress * 2));
                break;
            case R.id.sb_translationY:
                ivTest.setTranslationY(isCheck == true ? (progress * 2) : -(progress * 2));
                break;
            case R.id.sb_rotationX:
                ivTest.setRotationX(isCheck == true ? (progress * 2) : -(progress * 2));
                break;
            case R.id.sb_rotationY:
                ivTest.setRotationY(isCheck == true ? (progress * 2) : -(progress * 2));
                break;
            case R.id.sb_scaleX:
                ivTest.setScaleX(isCheck == true ? (progress * 2) : -(progress * 2));
                break;
            case R.id.sb_scaleY:
                ivTest.setScaleY(isCheck == true ? (progress * 2) : -(progress * 2));
                break;
            case R.id.sb_pivotX:
//                float pivotXValue   X轴起始点值
//              int pivotYType  Y轴起始点类型
//               float pivotYValue   Y轴起始点值
                ivTest.setPivotX(isCheck == true ? (progress * 4) : -(progress * 2));
                break;
            case R.id.sb_pivotY:
                ivTest.setRotationY(isCheck == true ? (progress * 2) : -(progress * 2));
                ivTest.setPivotY(isCheck == true ? (progress * 2) : -(progress * 2));
                break;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
