package com.lucidastar.hodgepodge.utils.fragment;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

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
