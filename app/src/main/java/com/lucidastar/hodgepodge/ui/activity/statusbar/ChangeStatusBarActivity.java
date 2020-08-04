package com.lucidastar.hodgepodge.ui.activity.statusbar;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.util.statusbar.StatusBarCompat;
//import com.jaeger.library.StatusBarUtil;
import com.lucidastar.hodgepodge.R;
import com.lucidastar.hodgepodge.utils.StatusBarUtil;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.Random;

public class ChangeStatusBarActivity extends AppCompatActivity

{

    private Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_status_bar);

//        initSystemBarTint();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("ceshi");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.colorPrimary),true);
    }

    public void initSystemBarTint() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus();
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintColor(getColorPrimary());
//            tintManager.setTintAlpha(0.0f);
        }
    }

    public void setTranslucentStatus() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window win = getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
//            int bits = WindowManager.LayoutParams.FLAG_FULLSCREEN;
            winParams.flags |= bits;
            win.setAttributes(winParams);
        }
    }

    public int getColorPrimary() {
        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
        return typedValue.data;
    }

    public int getColorPrimary1() {

        return getResources().getColor(R.color.primary_text);
    }

    public void change(View view){
        Random myRandom=new Random();
        int []ranColor ={0xff000000,0xFFFF4081,0xDE1BD255,0xDED96060,0xFFD99610,0x00ff0000}; //0xff000000 | random.nextInt(0x00ffffff);
        StatusBarCompat.setStatusBarColor(this, ranColor[myRandom.nextInt(6)]);

    }

    public void secondChange(View view){
        Random myRandom=new Random();
        int []ranColor ={0xff000000,0xFFFF4081,0xDE1BD255,0xDED96060,0xFFD99610,0x00ff0000};
        StatusBarUtil.setColor(this,ranColor[myRandom.nextInt(6)]);
    }

}
