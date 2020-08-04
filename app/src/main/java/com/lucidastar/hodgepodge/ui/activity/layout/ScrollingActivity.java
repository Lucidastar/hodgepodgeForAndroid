package com.lucidastar.hodgepodge.ui.activity.layout;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.lucidastar.hodgepodge.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ScrollingActivity extends AppCompatActivity {

    @BindView(R.id.app_bar)
    AppBarLayout mAppBar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbar_layout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    @BindView(R.id.btn_scroll_exitUntilCollapsed)
    Button mBtnScrollExitUntilCollapsed;
    @BindView(R.id.btn_scroll_enterAlwaysCollapsed)
    Button mBtnScrollEnterAlwaysCollapsed;
    @BindView(R.id.btn_scroll_snap)
    Button mBtnScrollSnap;
    @BindView(R.id.btn_toolbar_pin)
    Button mBtnToolbarPin;
    @BindView(R.id.btn_toolbar_parallax)
    Button mBtnToolbarParallax;
    @BindView(R.id.btn_CollapsingToolbarLayout_contentScrim)
    Button mBtnCollapsingToolbarLayoutContentScrim;
    @BindView(R.id.btn_scroll_enterAlways)
    Button mBtnScrollEnterAlways;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        ButterKnife.bind(this);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("Scroll的使用");
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });

//        AppBarLayout.LayoutParams layoutParams = (AppBarLayout.LayoutParams) toolbar_layout.getLayoutParams();
//        layoutParams.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL|AppBarLayout.LayoutParams.SCROLL_FLAG_SNAP);

    }

    private void setAttribute(View view) {
        AppBarLayout.LayoutParams layoutParams = (AppBarLayout.LayoutParams) toolbar_layout.getLayoutParams();
        switch (view.getId()) {
            case R.id.btn_scroll_exitUntilCollapsed:
                layoutParams.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL | AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED);
                break;
            case R.id.btn_scroll_enterAlwaysCollapsed:
                layoutParams.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL | AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS_COLLAPSED);
                break;
            case R.id.btn_scroll_snap:
                layoutParams.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL | AppBarLayout.LayoutParams.SCROLL_FLAG_SNAP);
                break;
            case R.id.btn_scroll_enterAlways:
                layoutParams.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL | AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS);
                break;
            case R.id.btn_toolbar_pin:
//                mToolbar.setOverScrollMode(Toolbar.Sc);

                break;
            case R.id.btn_toolbar_parallax:

                break;
            case R.id.btn_CollapsingToolbarLayout_contentScrim:
//                toolbar_layout.setContentScrimResource(ContextCompat.getDrawable(R.drawable.shape_et_bac));
                break;

        }
    }

    @OnClick({R.id.btn_scroll_exitUntilCollapsed, R.id.btn_scroll_enterAlwaysCollapsed, R.id.btn_scroll_snap, R.id.btn_scroll_enterAlways, R.id.btn_toolbar_pin, R.id.btn_toolbar_parallax, R.id.btn_CollapsingToolbarLayout_contentScrim})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_scroll_exitUntilCollapsed:
                setAttribute(mBtnScrollExitUntilCollapsed);
                break;
            case R.id.btn_scroll_enterAlwaysCollapsed:
                setAttribute(mBtnScrollEnterAlwaysCollapsed);
                break;
            case R.id.btn_scroll_snap:
                setAttribute(mBtnScrollSnap);
                break;
            case R.id.btn_toolbar_pin:
                break;
            case R.id.btn_toolbar_parallax:
                break;
            case R.id.btn_CollapsingToolbarLayout_contentScrim:
                break;
            case R.id.btn_scroll_enterAlways:
                setAttribute(mBtnScrollEnterAlways);
                break;
        }
    }
}
