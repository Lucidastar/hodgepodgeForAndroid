package com.lucidastar.hodgepodge.ui.fragment;


import android.content.Intent;
import android.view.View;

import com.lucidastar.hodgepodge.R;
import com.lucidastar.hodgepodge.ui.activity.bottomsheet.BottomSheetActivity;
import com.lucidastar.hodgepodge.ui.activity.layout.AppBarLayoutActivity;
import com.lucidastar.hodgepodge.ui.activity.layout.CollapsingToolbarActivity;
import com.lucidastar.hodgepodge.ui.activity.layout.RecycleViewActivity;
import com.lucidastar.hodgepodge.ui.activity.layout.ScrollingActivity;
import com.lucidastar.hodgepodge.ui.activity.layout.StudyFragmentActivity;
import com.lucidastar.hodgepodge.ui.activity.layout.StudyTabLayoutActivity;
import com.lucidastar.hodgepodge.ui.activity.layout.StudyViewPagerActivity;
import com.lucidastar.hodgepodge.ui.activity.layout.TestActivity;
import com.lucidastar.hodgepodge.ui.activity.layout.TextInputLayoutActivity;
import com.lucidastar.hodgepodge.ui.activity.layout.ToolBarActivity;
import com.lucidastar.hodgepodge.ui.base.BaseFragment;

import butterknife.OnClick;

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

    @OnClick({R.id.btn_bottom_sheet, R.id.btn_recycleView,R.id.btn_appBarLayout,R.id.btn_collapsingToolbar,R.id.btn_textInputLayout,R.id.btn_study_fragment,
            R.id.btn_study_scrolling,R.id.btn_study_toolbar,R.id.btn_study_tablayout,R.id.btn_study_view_pager})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_bottom_sheet:
                startActivity(new Intent(getActivity(), BottomSheetActivity.class));
                break;
            case R.id.btn_recycleView:
                startActivity(new Intent(getActivity(), RecycleViewActivity.class));
                break;
            case R.id.btn_appBarLayout:
                startActivity(new Intent(getActivity(), AppBarLayoutActivity.class));
                break;
            case R.id.btn_collapsingToolbar:
                startActivity(new Intent(getActivity(), CollapsingToolbarActivity.class));
                break;
            case R.id.btn_textInputLayout:
                startActivity(new Intent(getActivity(), TextInputLayoutActivity.class));
                break;
            case R.id.btn_study_fragment:
                startActivity(new Intent(getActivity(), StudyFragmentActivity.class));
                break;
            case R.id.btn_study_scrolling:
//                startActivity(new Intent(getActivity(), ScrollingActivity.class));
                startActivity(new Intent(getActivity(), TestActivity.class));
                break;
            case R.id.btn_study_toolbar:
                startActivity(new Intent(getActivity(), ToolBarActivity.class));
                break;
            case R.id.btn_study_tablayout:
                startActivity(new Intent(getActivity(), StudyTabLayoutActivity.class));
                break;

            case R.id.btn_study_view_pager:
                startActivity(new Intent(getActivity(), StudyViewPagerActivity.class));
                break;


        }
    }
}
