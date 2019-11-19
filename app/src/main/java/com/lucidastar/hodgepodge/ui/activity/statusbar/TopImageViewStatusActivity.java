package com.lucidastar.hodgepodge.ui.activity.statusbar;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.util.statusbar.StatusBarCompat;
import com.lucidastar.hodgepodge.R;
import com.lucidastar.hodgepodge.ui.activity.statusbar.fragment.TopImageViewStatusFragment;
import com.lucidastar.hodgepodge.utils.StatusBarUtil;

import java.util.List;

public class TopImageViewStatusActivity extends AppCompatActivity {

    private boolean isAdd = false;
    TopImageViewStatusFragment topImageViewStatusFragment;
    ImageView mIvTop;
//    LinearLayout mLlOffset;

    FrameLayout mFlContainer;
    Toolbar mToolBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_image_view_status);
        initView();
    }

    private void initView() {
//        StatusBarCompat.setStatusBarColor(this,getResources().getColor(R.color.trans),true);
//        mLlOffset = findViewById(R.id.ll_view_offset);
        mIvTop = findViewById(R.id.iv_top_image);
        mFlContainer = findViewById(R.id.fl_container);
        mToolBar = findViewById(R.id.tool_bar);
        //这个是设置全屏，并且设置颜色
        StatusBarUtil.setTranslucentForImageView(this,mToolBar);
//        StatusBarUtil.setTranslucentForImageViewInFragment(this, null);
        topImageViewStatusFragment = new TopImageViewStatusFragment();
        setFragment();
    }

    public void changeFragment(View view) {


        /*if (isAdd){
            mIvTop.setVisibility(View.VISIBLE);
//            mLlOffset.setVisibility(View.VISIBLE);
            getSupportFragmentManager().beginTransaction().remove(topImageViewStatusFragment);
            StatusBarUtil.setTranslucentForImageView(this,0,mFlContainer);
            isAdd = false;
        }else {
            mIvTop.setVisibility(View.GONE);
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_container,topImageViewStatusFragment).commit();
            isAdd = true;
            StatusBarUtil.setTranslucentForImageViewInFragment(this,0,null);
//            mLlOffset.setVisibility(View.GONE);
        }*/

    }

    //当activity中内部是fragment时，就这样使用，与ViewPager结合使用时也这样使用
    private void setFragment(){
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_container,topImageViewStatusFragment).commit();
        isAdd = true;
        StatusBarUtil.setTranslucentForImageViewInFragment(this,0,null);
    }
}
