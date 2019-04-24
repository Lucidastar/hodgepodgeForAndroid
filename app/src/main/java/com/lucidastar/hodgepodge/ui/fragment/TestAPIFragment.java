package com.lucidastar.hodgepodge.ui.fragment;

import android.content.Intent;
import android.view.View;

import com.lucidastar.hodgepodge.R;
import com.lucidastar.hodgepodge.ui.activity.api.MatrixStudyActivity;
import com.lucidastar.hodgepodge.ui.base.BaseFragment;

import butterknife.OnClick;

/**
 * API的学习列表
 */

public class TestAPIFragment extends BaseFragment {

    @Override
    protected void setUpView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_test_api_list;
    }

    @OnClick({R.id.btn_matrix})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_matrix:
            startActivity(new Intent(getActivity(), MatrixStudyActivity.class));
            break;
        }
    }
}
