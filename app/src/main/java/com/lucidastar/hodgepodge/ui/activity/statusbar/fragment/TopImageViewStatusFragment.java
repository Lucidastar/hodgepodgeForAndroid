package com.lucidastar.hodgepodge.ui.activity.statusbar.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.lucidastar.hodgepodge.R;
import com.lucidastar.hodgepodge.utils.StatusBarUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopImageViewStatusFragment extends Fragment {


    public TopImageViewStatusFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top_image_view_status, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void initView(View view) {
        LinearLayout mLlOffset = view.findViewById(R.id.ll_view_offset);

    }
}
