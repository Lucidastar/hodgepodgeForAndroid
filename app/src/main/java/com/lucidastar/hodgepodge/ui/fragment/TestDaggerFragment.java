package com.lucidastar.hodgepodge.ui.fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;

import com.lucidastar.hodgepodge.R;
import com.lucidastar.hodgepodge.ui.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * dagger的学习
 */
public class TestDaggerFragment extends BaseFragment {


    @BindView(R.id.btn_study)
    Button mBtnStudy;
    @BindView(R.id.btn_practice)
    Button mBtnPractice;

    public TestDaggerFragment() {
        // Required empty public constructor
    }


    @Override
    protected void setUpView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_test_dagger;
    }

    public void study(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri content_url = Uri.parse(url);
        intent.setData(content_url);
        startActivity(intent);
    }


    @OnClick({R.id.btn_study, R.id.btn_practice})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_study:
                final String[] articles = {"http://www.jianshu.com/p/1d84ba23f4d2#", "https://github.com/luxiaoming/dagger2Demo"};
                String[] titles = {"了解及使用", "图文详解"};
                AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).setTitle("选择文章").setItems(titles, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        study(articles[i]);
                    }
                }).create();
                alertDialog.show();
                break;
            case R.id.btn_practice:

                break;
        }
    }

}
