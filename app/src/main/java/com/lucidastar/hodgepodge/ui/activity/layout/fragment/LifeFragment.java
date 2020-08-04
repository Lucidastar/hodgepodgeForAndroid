package com.lucidastar.hodgepodge.ui.activity.layout.fragment;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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
    private int mPosition;
    public LifeFragment() {

    }

    public final static  LifeFragment getInstance(String titleName){
        LifeFragment lifeFragment = new LifeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("titleName",titleName);
        lifeFragment.setArguments(bundle);
        return lifeFragment;
    }

    public final static  LifeFragment getInstance(String titleName,int position){
        LifeFragment lifeFragment = new LifeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("titleName",titleName);
        bundle.putInt("position",position);
        lifeFragment.setArguments(bundle);
        return lifeFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        KLog.i(mPosition+":onCreateView");
        View view = inflater.inflate(R.layout.fragment_life, container, false);
        mTvName = view.findViewById(R.id.tv_name);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        KLog.i(mPosition+":onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        KLog.i(mPosition+":onCreate");
        Bundle arguments = getArguments();
        mTitleName = arguments.getString("titleName");
        mPosition = arguments.getInt("position");
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        KLog.i(mPosition+":onViewCreated");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        KLog.i(mPosition+":onActivityCreated");
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
        KLog.i(mPosition+":onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        KLog.i(mPosition+":onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        KLog.i(mPosition+":onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        KLog.i(mPosition+":onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        KLog.i(mPosition+":onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        KLog.i(mPosition+":onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        KLog.i(mPosition+":onDetach");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        KLog.i(mPosition+":setUserVisibleHint:  是否显示:"+isVisibleToUser);
    }
}
