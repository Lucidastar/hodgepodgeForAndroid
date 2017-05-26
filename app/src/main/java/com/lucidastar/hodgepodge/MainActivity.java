package com.lucidastar.hodgepodge;

import com.lucidastar.hodgepodge.ui.base.BaseActivity;
import com.socks.library.KLog;

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
        KLog.i("this is mainActivtiy");
    }
}
