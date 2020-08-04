package com.lucidastar.hodgepodge.utils.fragment;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.lucidastar.hodgepodge.utils.UtilInstance;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiuyouzone on 2019/4/29.
 */

public class ViewPagerFragment extends NavigationManager<Fragment> {
    private ViewPagerFragment.NavigationAdapter mNavigationAdapter;
    private FragmentManager mFragmentManager;
    private ViewPager mViewPager;
    private Fragment mFragment;
    protected List<Class<?>> mListClass = new ArrayList();

    public ViewPagerFragment(AppCompatActivity activity, ViewPager viewPager) {
        (this.mViewPager = viewPager).setAdapter(this.mNavigationAdapter = new ViewPagerFragment.NavigationAdapter(this.mFragmentManager = activity.getSupportFragmentManager()));
        this.mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                try {
                    ViewPagerFragment.this.mFragment = (Fragment) ViewPagerFragment.this.mList.get(position);
                    ViewPagerFragment.this.mCallBack.onNavigationChange(ViewPagerFragment.this.mFragment, position);
                } catch (Exception e) {

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public NavigationManager<Fragment> addFragment(Class[] classes) {
        for (int i = 0; i < classes.length; ++i) {
            try {
                this.mLinkedHashMap.put(classes[i], null);
            } catch (Exception e) {

            }
        }
        this.notifyDataSetChanged();
        return this;
    }

    @Override
    public NavigationManager<Fragment> addFragment(Fragment... fragments) {
        for (int i = 0; i < fragments.length; ++i) {
            try {
                this.mLinkedHashMap.put(fragments[i].getClass(), fragments[i]);
            } catch (Exception e) {
            }
        }

        this.notifyDataSetChanged();
        return this;
    }

    @Override
    public void show(Class<? extends Fragment> c) {
        if (this.mLinkedHashMap.containsKey(c)) {
            this.show(this.mListClass.indexOf(c));
        } else {
            try {
                this.mLinkedHashMap.put(c, UtilInstance.Instance(c));
                this.notifyDataSetChanged();
                this.show(this.mListClass.indexOf(c));
            } catch (Exception e) {

            }
        }
    }

    @Override
    public void show(Fragment fragment) {
        if (this.mList.contains(fragment)) {
            this.show(this.mListClass.indexOf(fragment));
        } else {
            try {
                this.mLinkedHashMap.put(fragment.getClass(), fragment);
                this.notifyDataSetChanged();
                this.show(this.mListClass.indexOf(fragment));
            } catch (Exception e) {

            }
        }
    }

    @Override
    public boolean show(Bundle bundle) {
        boolean flag = bundle == null ? false : (this.mFragment = this.mFragmentManager.getFragment(bundle, "cf")) != null;
        if (flag) {
            this.show(this.mFragment);
        }
        return flag;
    }

    @Override
    public void show(int item) {
        try {
            this.mViewPager.setCurrentItem(item);
        } catch (Exception e) {

        }
    }

    @Override
    public void remove(Class[] classes) {
        for (int i = 0; i < classes.length; ++i) {
            try {
                this.mLinkedHashMap.remove(classes[i]);
            } catch (Exception e) {
                ;
            }
        }

        this.notifyDataSetChanged();
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        try {
            this.mFragmentManager.putFragment(bundle, "cf", this.mFragment);
        } catch (Exception e) {

        }
    }

    @Override
    public void setOnNavigationChangeCallBack(OnNavigationChangeCallBack<Fragment> callBack) {
        this.mCallBack = callBack;
    }

    @Override
    protected void load(Fragment fragment) throws Exception {

    }

    @Override
    protected void notifyDataSetChanged() {
        this.mListClass.clear();
        this.mList.clear();
        this.mListClass.addAll(this.mLinkedHashMap.keySet());
        this.mList.addAll(this.mLinkedHashMap.values());
        this.mNavigationAdapter.notifyDataSetChanged();
    }

    private class NavigationAdapter extends FragmentPagerAdapter {
        public NavigationAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = (Fragment) ViewPagerFragment.this.mList.get(position);
            if (fragment == null) {
                try {
                    Class<Fragment> cls = (Class) ViewPagerFragment.this.mListClass.get(position);
                    ViewPagerFragment.this.mLinkedHashMap.put(cls, fragment = (Fragment) UtilInstance.Instance(cls));
                    ViewPagerFragment.this.mList.remove(position);
                    ViewPagerFragment.this.mList.add(position, fragment);
                } catch (Exception e) {

                }
            }
            return fragment;
        }

        public int getCount() {
            return ViewPagerFragment.this.mList.size();
        }

    }


}
