package com.lucidastar.hodgepodge.ui.fragment;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;

import com.lucidastar.hodgepodge.R;
import com.lucidastar.hodgepodge.ui.activity.bottomsheet.BottomSheetActivity;
import com.lucidastar.hodgepodge.ui.base.BaseFragment;

import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class WidgetListFragment extends BaseFragment {


    public WidgetListFragment() {
        // Required empty public constructor
    }


    @Override
    protected void setUpView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_widget_list;
    }

    @OnClick({R.id.btn_bottom_sheet, R.id.btn_recycleView})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_bottom_sheet:
                startActivity(new Intent(getActivity(), BottomSheetActivity.class));
                break;
            case R.id.btn_recycleView:
                break;
        }
    }
}
