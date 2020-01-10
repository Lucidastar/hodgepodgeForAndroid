package com.lucidastar.hodgepodge.ui.activity.layout.fragment;


import android.arch.lifecycle.Lifecycle;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lucidastar.hodgepodge.R;
import com.mine.lucidastarutils.log.KLog;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class LifeFragment extends Fragment {


    TextView mTvName;
    private String mTitleName;
    private List<String> mDatas = new ArrayList<>();
    public LifeFragment() {

    }

    public final static  LifeFragment getInstance(String titleName){
        LifeFragment lifeFragment = new LifeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("titleName",titleName);
        lifeFragment.setArguments(bundle);
        return lifeFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        KLog.i("onCreateView");
        View view = inflater.inflate(R.layout.fragment_life, container, false);
        mTvName = view.findViewById(R.id.tv_name);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        KLog.i("onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        KLog.i("onCreate");
        Bundle arguments = getArguments();
        mTitleName = arguments.getString("titleName");
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        KLog.i("onViewCreated");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        KLog.i("onActivityCreated");
        mTvName.setText(mTitleName);
        initData();
    }

    private void initData() {
        mDatas.clear();
        for (int i = 0; i < 10; i++) {
            mDatas.add(mTitleName);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        KLog.i("onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        KLog.i("onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        KLog.i("onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        KLog.i("onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        KLog.i("onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        KLog.i("onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        KLog.i("onDetach");
    }
}
