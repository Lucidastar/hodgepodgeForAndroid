package com.lucidastar.hodgepodge.ui.base;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.lucidastar.hodgepodge.R;
import com.mine.lucidastarutils.log.KLog;
import com.readystatesoftware.systembartint.SystemBarTintManager;

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
        mUnbinder = ButterKnife.bind(this);
        initViews();
        initToolBar();
        initInjector();
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

    public void initTooBarAndFinish(@StringRes int titleName){
        if (toolbar != null){
            toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);//设置返回按钮
            toolbar.setTitleTextColor(getResources().getColor(R.color.white));
            setTitleName(titleName);
            setSupportActionBar(toolbar);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
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
