package com.lucidastar.hodgepodge.ui.activity.layout;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.lucidastar.hodgepodge.R;
import com.lucidastar.hodgepodge.ui.activity.layout.adapter.ViewPagerAdapter;
import com.lucidastar.hodgepodge.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

public class StudyViewPagerActivity extends BaseActivity {

    @BindView(R.id.vp_fragment)
    ViewPager mViewPager;

    @BindView(R.id.tl_title)
    TabLayout mTabLayout;

    ViewPagerAdapter mViewPagerAdapter;

    private List<String> mTitleNames = new ArrayList<>();
    @Override
    public int getLayoutId() {
        return R.layout.activity_study_view_pager;
    }

    @Override
    public void initInjector() {

    }

    @Override
    public void initViews() {
        mTitleNames.addAll(Arrays.asList("标题1","标题2","标题3","标题4","标题5","标题6","标题7","标题8","标题9","标题10"));
        mViewPager.setAdapter(mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),mTitleNames,mContext));
        for (int i = 0; i < mTitleNames.size(); i++) {
            mTabLayout.addTab(mTabLayout.newTab());
        }
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void initToolBar() {
        toolbar = findViewById(R.id.toolbar);
        initTooBarAndFinish(R.string.layout_title_view_pager_study);
    }

}
