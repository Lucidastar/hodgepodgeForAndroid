package com.lucidastar.hodgepodge;

import android.graphics.drawable.Drawable;

import com.lucidastar.hodgepodge.ui.base.BaseActivity;


public class MainActivity extends BaseActivity {


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initInjector() {

    }

    @Override
    public void initViews() {
        mIsHasNavigationView = true;

    }
}
