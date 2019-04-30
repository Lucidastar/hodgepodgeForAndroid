package com.lucidastar.hodgepodge.utils.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by qiuyouzone on 2019/4/30.
 */

public final class NavigationManagerFactory {
    private NavigationManagerFactory() {
    }
    public static NavigationManager<Fragment> createV4(AppCompatActivity activity, int layoutId) {
        return new TabFragment(activity, layoutId);
    }

    public static NavigationManager<Fragment> createV4Pager(AppCompatActivity activity, ViewPager viewPager) {
        return new ViewPagerFragment(activity, viewPager);
    }
}
