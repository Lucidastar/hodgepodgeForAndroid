package com.lucidastar.hodgepodge.ui.activity.layout.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.lucidastar.hodgepodge.ui.activity.layout.fragment.LifeFragment;
import com.lucidastar.hodgepodge.ui.activity.layout.fragment.PhotoFragment;

import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private List<String> mTitles ;
    private Context mContext;

    public ViewPagerAdapter(FragmentManager fm, List<String> titles, Context context) {
        super(fm);
        mTitles = titles;
        mContext = context;
    }

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return LifeFragment.getInstance(mTitles.get(i));
    }

    @Override
    public int getCount() {
        return mTitles.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }
}
