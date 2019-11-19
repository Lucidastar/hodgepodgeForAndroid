package com.lucidastar.hodgepodge.ui.activity.statusbar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.lucidastar.hodgepodge.R;
import com.lucidastar.hodgepodge.ui.activity.statusbar.fragment.TopImageViewStatusFragment;
import com.lucidastar.hodgepodge.utils.StatusBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TopFragmentActivity extends AppCompatActivity {

    @BindView(R.id.fl_container)
    FrameLayout flContainer;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_fragment);
        ButterKnife.bind(this);
        StatusBarUtil.setTranslucentForImageViewInFragment(this,0,toolBar);
        initView();
    }

    private void initView() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_container,new TopImageViewStatusFragment()).commit();
    }
}
