package com.lucidastar.hodgepodge.utils.fragment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.lucidastar.hodgepodge.utils.UtilInstance;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by qiuyouzone on 2019/4/29.
 */

public class TabFragment extends NavigationManager<Fragment> {

    private FragmentManager mFragmentManager;
    private Fragment mFragment;
    private int mLayout;

    public TabFragment(AppCompatActivity activity, int layout) {
        this.mFragmentManager = activity.getSupportFragmentManager();
        this.mLayout = layout;
    }

    @Override
    public NavigationManager<Fragment> addFragment(Class[] classes) {
        for(int i = 0; i < classes.length; ++i) {
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
        for(int i = 0; i < fragments.length; ++i) {
            try {
                this.mLinkedHashMap.put(fragments[i].getClass(), fragments[i]);
            } catch (Exception e) {
                ;
            }
        }

        this.notifyDataSetChanged();
        return this;
    }

    @Override
    public void show(Class<? extends Fragment> clazz) {
        Fragment r = null;
        if(this.mLinkedHashMap.containsKey(clazz)) {
            if((r = (Fragment)this.mLinkedHashMap.get(clazz)) == null) {
                try {
                    r = (Fragment) UtilInstance.Instance(clazz);
                    this.mLinkedHashMap.put(clazz, r);
                    this.notifyDataSetChanged();
                } catch (Exception e) {

                }
            }
        } else {
            try {
                r = (Fragment)UtilInstance.Instance(clazz);
                this.mLinkedHashMap.put(clazz, r);
                this.notifyDataSetChanged();
            } catch (Exception e) {
                ;
            }
        }
        try {
            this.load(r);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void show(Fragment fragment) {
        try {
            if(!this.mLinkedHashMap.containsValue(fragment)) {
                this.mLinkedHashMap.put(fragment.getClass(), fragment);
                this.notifyDataSetChanged();
            }
            this.load(fragment);
        } catch (Exception e) {
            ;
        }
    }

    @Override
    public boolean show(Bundle bundle) {
        boolean flag = bundle == null?false:(this.mFragment= this.mFragmentManager.getFragment(bundle, "cf")) != null;
        if(flag) {
            this.show(this.mFragment);
        }
        return flag;
    }

    @Override
    public void show(int item) {
        this.show((Class)(new ArrayList(this.mLinkedHashMap.keySet())).get(item));
    }

    @Override
    public void remove(Class[] classes) {
        FragmentTransaction transaction = this.mFragmentManager.beginTransaction();
        for(int i = 0; i < classes.length; ++i) {
            try {
                transaction.remove((Fragment)this.mLinkedHashMap.get(classes[i]));
                this.mLinkedHashMap.remove(classes[i]);
                this.notifyDataSetChanged();
            } catch (Exception e) {

            }
        }
        transaction.commitAllowingStateLoss();
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
        FragmentTransaction transaction = this.mFragmentManager.beginTransaction();
        Iterator iterator = this.mLinkedHashMap.values().iterator();
        while(iterator.hasNext()) {
            Fragment f = (Fragment)iterator.next();
            if(f != null) {
                transaction.hide(f);
            }
        }
        if(fragment.isAdded()) {
            transaction.show(fragment);
        } else {
            transaction.add(this.mLayout, fragment).show(fragment);
        }
        transaction.commitAllowingStateLoss();
        try {
            this.mCallBack.onNavigationChange(fragment, this.mList.indexOf(fragment));
        } catch (Exception e) {

        }
        this.mFragment = fragment;
    }

    @Override
    protected void notifyDataSetChanged() {
        this.mList.clear();
        this.mList.addAll(this.mLinkedHashMap.values());
    }
}
