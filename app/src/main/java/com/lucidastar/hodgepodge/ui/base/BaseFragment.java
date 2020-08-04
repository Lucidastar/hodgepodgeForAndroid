package com.lucidastar.hodgepodge.ui.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;

/**
 * Created by hanxiaoxing on 16/12/27.
 */

public abstract class BaseFragment extends Fragment {


    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(getLayoutId(),container,false);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        setUpView();

    }

    //实例化其他view
    protected abstract void setUpView();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        parseArguments();
    }

    protected abstract int getLayoutId();


    protected View getParentView(){
        return view;
    }

    /**
     * parse argument
     */
    protected void parseArguments(){

    }
}
