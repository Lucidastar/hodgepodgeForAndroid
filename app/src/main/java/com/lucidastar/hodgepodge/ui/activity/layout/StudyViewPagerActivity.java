package com.lucidastar.hodgepodge.ui.activity.layout;

import android.view.View;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.lucidastar.hodgepodge.R;
import com.lucidastar.hodgepodge.ui.activity.layout.adapter.ViewPagerAdapter;
import com.lucidastar.hodgepodge.ui.base.BaseActivity;
import com.mine.lucidastarutils.log.KLog;

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
        initListener();
    }

    private void initListener() {
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int childCount = mViewPager.getChildCount();
                KLog.d("数量==:"+childCount);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void initViews() {
        mTitleNames.addAll(Arrays.asList("标题1","标题2","标题3","标题4","标题5","标题6","标题7","标题8","标题9","标题10"));
        mViewPager.setAdapter(mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),mTitleNames,mContext));
        for (int i = 0; i < mTitleNames.size(); i++) {
            mTabLayout.addTab(mTabLayout.newTab());
        }
        mTabLayout.setupWithViewPager(mViewPager);

        mViewPagerAdapter.notifyDataSetChanged();
        int childCount = mViewPager.getChildCount();

        KLog.d("数量:"+childCount);
//        mViewPager.setOffscreenPageLimit(mTitleNames.size());
    }

    @Override
    public void initToolBar() {
        toolbar = findViewById(R.id.toolbar);
        initTooBarAndFinish(R.string.layout_title_view_pager_study);
    }

    //删除一个tab
    private void deleteOneTab(){

    }
    //添加一个tab
    private void addOneTab(){

    }
    //中间删除一个tab
    private void deleteMeddleTab(){

    }
    //定位到一个tab
    private void toOneTab(){

    }



}
