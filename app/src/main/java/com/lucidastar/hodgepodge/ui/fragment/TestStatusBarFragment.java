package com.lucidastar.hodgepodge.ui.fragment;


import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;

import com.lucidastar.hodgepodge.R;
import com.lucidastar.hodgepodge.ui.activity.statusbar.ChangeStatusBarActivity;
import com.lucidastar.hodgepodge.ui.activity.statusbar.ToolBarSearchActivity;
import com.lucidastar.hodgepodge.ui.activity.statusbar.ToolBarToUse;
import com.lucidastar.hodgepodge.ui.activity.statusbar.TopFragmentActivity;
import com.lucidastar.hodgepodge.ui.activity.statusbar.TopImageViewStatusActivity;
import com.lucidastar.hodgepodge.ui.base.BaseFragment;


import butterknife.BindView;
import butterknife.OnClick;

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


    @OnClick({R.id.studyToobar, R.id.changeBar,R.id.studySearch,R.id.btn_practice,R.id.status_top_image,R.id.status_top_fragment})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.studyToobar:
                startActivity(new Intent(getContext(), ToolBarToUse.class));
                break;
            case R.id.changeBar:
//                Utils.init(getActivity());
//                ToastUtils.showShortToast("changeBar");
                startActivity(new Intent(getContext(), ChangeStatusBarActivity.class));
                break;

            case R.id.studySearch:
                startActivity(new Intent(getContext(), ToolBarSearchActivity.class));
                break;

            case R.id.btn_practice:
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.jianshu.com/p/e2ae6aaff696"));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            case R.id.status_top_image:
                startActivity(new Intent(getContext(), TopImageViewStatusActivity.class));

                break;
            case R.id.status_top_fragment:
                startActivity(new Intent(getContext(), TopFragmentActivity.class));
                break;
        }
    }
}
