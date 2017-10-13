package com.lucidastar.hodgepodge.ui.fragment;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Toast;

import com.lucidastar.hodgepodge.R;
import com.lucidastar.hodgepodge.ui.activity.custom.CustomActivity;
import com.lucidastar.hodgepodge.ui.activity.otherfeature.TestBitmapActivity;
import com.lucidastar.hodgepodge.ui.activity.otherfeature.animation.AnimationActivity;
import com.lucidastar.hodgepodge.ui.activity.otherfeature.handler.TestHandlerActivity;
import com.lucidastar.hodgepodge.ui.activity.otherfeature.saidl.StudentAidlActivity;
import com.lucidastar.hodgepodge.ui.base.BaseFragment;
import com.lucidastar.hodgepodge.view.CustomDialogFragment;

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

    @OnClick({R.id.btn_study_handler, R.id.btn_study_other,R.id.btn_study_bitmap,R.id.btn_study_aidl,R.id.btn_study_fragment_dialog,R.id.btn_study_custom_view,R.id.btn_study_animation})
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

            case R.id.btn_study_fragment_dialog:
                String content = "1、界面优化;2、加载数据的bug";
                 CustomDialogFragment customDialogFragment = CustomDialogFragment.getInstance(content,false);
//                customDialogFragment.setRetainInstance(true);
                customDialogFragment.setDialogButtonClickListener(new CustomDialogFragment.OnDialogButtonClickListener() {
                    @Override
                    public void cancelButtonClick() {
                        Toast.makeText(getActivity(),"取消",Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void confirmButtonClick() {
                        Toast.makeText(getActivity(),"开始下载",Toast.LENGTH_SHORT).show();
                    }
                });
                customDialogFragment.show(getFragmentManager(),"customDialog");
                break;
            case R.id.btn_study_custom_view:
                startActivity(new Intent(getActivity(), CustomActivity.class));
                break;

            case R.id.btn_study_animation:
                startActivity(new Intent(getActivity(), AnimationActivity.class));
                break;
        }
    }
}
