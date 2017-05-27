package com.lucidastar.hodgepodge.ui.fragment;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;

import com.lucidastar.hodgepodge.R;
import com.lucidastar.hodgepodge.ui.activity.otherfeature.TestBitmapActivity;
import com.lucidastar.hodgepodge.ui.activity.otherfeature.handler.TestHandlerActivity;
import com.lucidastar.hodgepodge.ui.activity.otherfeature.saidl.StudentAidlActivity;
import com.lucidastar.hodgepodge.ui.base.BaseFragment;

import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class OtherFeatureFragment extends BaseFragment {


    public OtherFeatureFragment() {
        // Required empty public constructor
    }


    @Override
    protected void setUpView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_other_feature;
    }

    @OnClick({R.id.btn_study_handler, R.id.btn_study_other,R.id.btn_study_bitmap,R.id.btn_study_aidl})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_study_handler:
                startActivity(new Intent(getActivity(), TestHandlerActivity.class));
                break;
            case R.id.btn_study_other:
                break;
            case R.id.btn_study_bitmap:
                startActivity(new Intent(getActivity(), TestBitmapActivity.class));
                break;

            case R.id.btn_study_aidl:
                startActivity(new Intent(getActivity(), StudentAidlActivity.class));
                break;
        }
    }
}