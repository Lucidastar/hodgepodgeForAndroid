package com.lucidastar.hodgepodge;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import com.lucidastar.hodgepodge.ui.base.BaseActivity;
import com.lucidastar.hodgepodge.ui.base.BaseFragment;
import com.lucidastar.hodgepodge.ui.fragment.DemoListFragment;
import com.lucidastar.hodgepodge.ui.fragment.OtherFeatureFragment;
import com.lucidastar.hodgepodge.ui.fragment.TestAPIFragment;
import com.lucidastar.hodgepodge.ui.fragment.TestDaggerFragment;
import com.lucidastar.hodgepodge.ui.fragment.TestIntentServiceFragment;
import com.lucidastar.hodgepodge.ui.fragment.TestStatusBarFragment;
import com.lucidastar.hodgepodge.ui.fragment.WidgetListFragment;
import com.mine.lucidastarutils.utils.ToastUtils;


public class MainActivity extends BaseActivity {

    protected boolean mIsHasNavigationView;
    private DrawerLayout mDrawerLayout;
    private Class mClass;

    public int lastFragmentIndex = 0;
    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initInjector() {
        if (mIsHasNavigationView) {
            initDrawerLayout();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fl_container, getLeftFragment(1), makeTag(1)).commit();


    }

    @Override
    public void initViews() {
        mIsHasNavigationView = true;
    }

    @Override
    public void initToolBar() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.TestIntentService);
        setSupportActionBar(toolbar);
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
            case 3:
                fragment = new TestDaggerFragment();
                break;
            case 4:
                fragment = new WidgetListFragment();
                break;
            case 5:
                fragment = new OtherFeatureFragment();
                break;
            case 6:
                fragment = new DemoListFragment();
                break;
            case 7:
                fragment = new TestAPIFragment();
                break;
        }
        return fragment;
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

    private void initDrawerLayout() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//        toolbar = (Toolbar) findViewById(R.id.toolbar);
        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);

        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (navView != null) {
            navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.nav_test_inservice:
                            ToastUtils.showShortToast("舍弃");
//                            controlShowFragment(1);
//                            toolbar.setTitle(R.string.TestIntentService);
                            break;
                        case R.id.nav_test_status:
                            controlShowFragment(2);
                            toolbar.setTitle(R.string.TestStatusBar);
                            break;
                        case R.id.nav_test_dagger:
                            ToastUtils.showShortToast("舍弃");
//                            controlShowFragment(3);
//                            toolbar.setTitle(R.string.TestDagger);
                            break;
                        case R.id.nav_test_widget:
                            controlShowFragment(4);
                            toolbar.setTitle(R.string.TestWidget);
                            break;
                        case R.id.nav_test_other_feature:
                            controlShowFragment(5);
                            toolbar.setTitle(R.string.TestOtherFeature);
                            break;
                        case R.id. nav_test_demo_list:
                            controlShowFragment(6);
                            toolbar.setTitle(R.string.TestDemoList);
                            break;
                        case R.id. nav_test_api_list:
                            controlShowFragment(7);
                            toolbar.setTitle(R.string.TestAPIList);
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
                    Intent intent = new Intent(mContext, mClass);
                    // 此标志用于启动一个Activity的时候，若栈中存在此Activity实例，则把它调到栈顶。不创建多一个
//                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent);
                    overridePendingTransition(0, 0);
                    mClass = null;
                }
            }
        });
    }
}
