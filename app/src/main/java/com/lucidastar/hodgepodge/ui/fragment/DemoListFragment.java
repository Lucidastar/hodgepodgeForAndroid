package com.lucidastar.hodgepodge.ui.fragment;


import android.content.Intent;

import com.lucidastar.hodgepodge.R;
import com.lucidastar.hodgepodge.ui.activity.demolist.picandcomment.PicAndCommentActivity;
import com.lucidastar.hodgepodge.ui.base.BaseFragment;

import butterknife.OnClick;


public class DemoListFragment extends BaseFragment {

    public DemoListFragment() {
        // Required empty public constructor
    }


    @Override
    protected void setUpView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_demo_list;
    }



    @OnClick(R.id.btn_picAndComment)
    public void onViewClicked() {
        startActivity(new Intent(getActivity(), PicAndCommentActivity.class));
    }
}
