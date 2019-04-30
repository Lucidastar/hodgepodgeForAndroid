package com.lucidastar.hodgepodge.utils.fragment;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by qiuyouzone on 2019/4/29.
 */

public abstract class NavigationManager <T> {
    protected LinkedHashMap<Class<?>, T> mLinkedHashMap = new LinkedHashMap();
    protected OnNavigationChangeCallBack<T> mCallBack;
    protected List<T> mList = new ArrayList();

    public NavigationManager() {
    }

    public abstract NavigationManager<T> addFragment(Class... classes);

    public abstract NavigationManager<T> addFragment(T... t);

    public abstract void show(Class<? extends T> c);

    public abstract void show(T t);

    public abstract boolean show(Bundle bundle);

    public abstract void show(int item);

    public abstract void remove(Class... classes);

    public abstract void onSaveInstanceState(Bundle bundle);

    public abstract void setOnNavigationChangeCallBack(OnNavigationChangeCallBack<T> callBack);

    protected abstract void load(T t) throws Exception;

    protected abstract void notifyDataSetChanged();
}
