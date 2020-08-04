package com.lucidastar.hodgepodge.ui.activity.layout;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputLayout;
import com.lucidastar.hodgepodge.R;
import com.mine.lucidastarutils.utils.RegularUtils;
import com.mine.lucidastarutils.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TextInputLayoutActivity extends AppCompatActivity {

    @BindView(R.id.et_phoneNum)
    EditText etPhoneNum;
    @BindView(R.id.til_phoneNum)
    TextInputLayout tilPhoneNum;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.til_password)
    TextInputLayout tilPassword;
    private Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_input_layout);
        ButterKnife.bind(this);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("这是TextInputLayout");
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        etPhoneNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 11) {
                    tilPhoneNum.setErrorEnabled(true);
                    tilPhoneNum.setError("手机号最长为11位");
                } else {
                    if (RegularUtils.isPhoneNumExact(s)){
                        tilPhoneNum.setErrorEnabled(false);
                    }else {
                        tilPhoneNum.setErrorEnabled(true) ;
                        tilPhoneNum.setError("手机号格式不正确");
                    }

                }
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });

        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() < 6) {
                    tilPassword.setErrorEnabled(true);
                    tilPassword.setError("密码最小长度为6位");
                } else {
                    if (s.length() > 6 ){
                        tilPassword.setErrorEnabled(true);
                        if (RegularUtils.checkPasswordStrength(s) == 1){
                            tilPassword.setError("密码强度弱");
                        }else if (RegularUtils.checkPasswordStrength(s) == 2){
                            tilPassword.setError("密码强度弱");
                        }else if (RegularUtils.checkPasswordStrength(s) == 3){
                            tilPassword.setError("密码强度强");
                        }else {
                            tilPassword.setError("密码强度强");
                        }
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

    }

    public void login(View view) {
        ToastUtils.showShortToast("登录一下");
    }
}
