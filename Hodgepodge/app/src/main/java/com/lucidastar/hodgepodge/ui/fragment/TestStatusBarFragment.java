package com.lucidastar.hodgepodge.ui.fragment;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;

import com.lucidastar.hodgepodge.R;
import com.lucidastar.hodgepodge.ui.activity.statusbar.ChangeStatusBarActivity;
import com.lucidastar.hodgepodge.ui.base.BaseFragment;
import com.lucidastar.hodgepodge.utils.ToastUtils;
import com.lucidastar.hodgepodge.utils.Utils;
import com.lucidastar.testlib.TestMyLib;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class TestStatusBarFragment extends BaseFragment {


    @BindView(R.id.studyToobar)
    Button mStudyToobar;
    @BindView(R.id.changeBar)
    Button mChangeBar;

    public TestStatusBarFragment() {
        // Required empty public constructor
    }

    @Override
    protected void setUpView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_test_status_bar;
    }


    @OnClick({R.id.studyToobar, R.id.changeBar})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.studyToobar:
                Utils.init(getActivity());

                String test = TestMyLib.getTest();
                mStudyToobar.setText(test);
                ToastUtils.showShortToast(test);
                break;
            case R.id.changeBar:
                Utils.init(getActivity());
                ToastUtils.showShortToast("changeBar");
                startActivity(new Intent(getContext(), ChangeStatusBarActivity.class));
                break;
        }
    }
}
