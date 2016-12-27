package com.lucidastar.hodgepodge.ui.base;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
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
import com.lucidastar.hodgepodge.ui.fragment.TestIntentServiceFragment;
import com.lucidastar.hodgepodge.ui.fragment.TestStatusBarFragment;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.socks.library.KLog;

import butterknife.ButterKnife;

/**
 * Created by hanxiaoxing on 16/12/26.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private WindowManager mWindowManager = null;
    private boolean mIsAddedView;
    protected boolean mIsHasNavigationView;
    private DrawerLayout mDrawerLayout;
    private Class mClass;

    public abstract int getLayoutId();

    public abstract void initInjector();

    public abstract void initViews();


    public int lastFragmentIndex = 0;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        KLog.i(getClass().getSimpleName());
        int layoutId = getLayoutId();
        setContentView(layoutId);
        initInjector();
        ButterKnife.bind(this);
        initToolBar();
        initViews();
        if (mIsHasNavigationView) {
            initDrawerLayout();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fl_container, getLeftFragment(1), makeTag(1)).commit();
//        initSystemBarTint();
    }
    private void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.TestIntentService);
        setSupportActionBar(toolbar);
    }


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
    private void initDrawerLayout() {

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//        toolbar = (Toolbar) findViewById(R.id.toolbar);
        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (navView != null) {
            navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.nav_test_inservice:
                            controlShowFragment(1);
                            toolbar.setTitle(R.string.TestIntentService);
                            break;
                        case R.id.nav_test_status:
                            controlShowFragment(2);
                            toolbar.setTitle(R.string.TestStatusBar);
                            break;

                    }
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                    return false;
                }
            });
        }

        mDrawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                if (mClass != null) {
                    Intent intent = new Intent(BaseActivity.this, mClass);
                    // 此标志用于启动一个Activity的时候，若栈中存在此Activity实例，则把它调到栈顶。不创建多一个
//                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent);
                    overridePendingTransition(0, 0);
                    mClass = null;
                }
            }
        });
    }

    //显示相应的Fragment
    private void controlShowFragment(int position) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment lastFragment = fragmentManager.findFragmentByTag(makeTag(lastFragmentIndex));
        if (lastFragment != null) {
            fragmentTransaction.hide(lastFragment);
        }
        lastFragmentIndex = position;

        Fragment currentFragment = fragmentManager.findFragmentByTag(makeTag(position));
        if (currentFragment != null) {
            fragmentTransaction.show(currentFragment);
        } else {
            if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
                fragmentTransaction.add(R.id.fl_container, getLeftFragment(position), makeTag(position));
            }
        }
        fragmentTransaction.commitAllowingStateLoss();
    }

    private String makeTag(int position) {
        return R.id.fl_container + "_" + position;
    }

    private BaseFragment getLeftFragment(int position) {
        BaseFragment fragment = null;
        switch (position) {
            case 1:
                fragment = new TestIntentServiceFragment();
                break;
            case 2:
                fragment = new TestStatusBarFragment();
                break;
        }
        return fragment;
    }

}
