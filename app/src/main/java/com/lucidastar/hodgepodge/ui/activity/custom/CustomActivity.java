package com.lucidastar.hodgepodge.ui.activity.custom;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.lucidastar.hodgepodge.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CustomActivity extends AppCompatActivity {

    @BindView(R.id.btn_study_custom_calender)
    Button btnStudyCustomCalender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_study_custom_calender)
    public void onViewClicked() {
        startActivity(new Intent(this,CustomCalendarActivity.class));
    }
}
