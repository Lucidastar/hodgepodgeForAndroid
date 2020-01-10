package com.lucidastar.hodgepodge.ui.base;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.lucidastar.hodgepodge.R;
import com.lucidastar.hodgepodge.ui.fragment.DemoListFragment;
import com.lucidastar.hodgepodge.ui.fragment.OtherFeatureFragment;
import com.lucidastar.hodgepodge.ui.fragment.TestAPIFragment;
import com.lucidastar.hodgepodge.ui.fragment.TestDaggerFragment;
import com.lucidastar.hodgepodge.ui.fragment.TestIntentServiceFragment;
import com.lucidastar.hodgepodge.ui.fragment.TestStatusBarFragment;
import com.lucidastar.hodgepodge.ui.fragment.WidgetListFragment;
import com.mine.lucidastarutils.log.KLog;
import com.mine.lucidastarutils.utils.ToastUtils;
import com.readystatesoftware.systembartint.SystemBarTintManager;


import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by hanxiaoxing on 16/12/26.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected Context mContext;
    private Unbinder mUnbinder;

    protected Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        KLog.i(getClass().getSimpleName());
        mContext = this;
        setContentView(getLayoutId());
        initViews();
        initInjector();
        initToolBar();
        mUnbinder = ButterKnife.bind(this);

    }

    public abstract int getLayoutId();

    public abstract void initInjector();

    public abstract void initViews();
    public abstract void initToolBar();


    public void initSystemBarTint() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus();
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintColor(getColorPrimary());
        }
    }

    public void setTranslucentStatus() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window win = getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            winParams.flags |= bits;
            win.setAttributes(winParams);
        }
    }

    public int getColorPrimary() {
        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
        return typedValue.data;
    }



    protected void setTitleName(CharSequence titleName){
        if (toolbar != null){
            toolbar.setTitle(titleName);
        }
    }


    protected void setTitleName(@StringRes int titleName){
        if (toolbar != null){
            toolbar.setTitle(titleName);
        }
    }


    protected void startActivityClass(Class<?> clazz){
        this.startActivityClass(clazz,new Intent());
    }

    protected void startActivityClass(Class<?> clazz,Intent intent){
        startActivity(intent.setClass(this,clazz));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null){
            mUnbinder.unbind();
        }
    }
}
