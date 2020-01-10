package com.lucidastar.hodgepodge.ui.activity.layout;

import com.lucidastar.hodgepodge.R;
import com.lucidastar.hodgepodge.ui.base.BaseActivity;

public class StudyViewPagerActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_study_view_pager;
    }

    @Override
    public void initInjector() {

    }

    @Override
    public void initViews() {
    }

    @Override
    public void initToolBar() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);//设置返回按钮
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setTitleName(R.string.layout_title_view_pager_study);
        setSupportActionBar(toolbar);
    }

}
