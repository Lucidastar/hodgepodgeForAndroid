package com.lucidastar.hodgepodge.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.lucidastar.hodgepodge.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by qiuyouzone on 2017/9/18.
 */

public class CustomDialogFragment extends DialogFragment implements View.OnClickListener {

    private static final String TAG = "CustomDialogFragment";
    @BindView(R.id.tv_version_content)
    TextView tvUpdateContent;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.tv_confirm)
    TextView tvConfirm;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    Unbinder unbinder;
    OnDialogButtonClickListener dialogButtonClickListener;
    private boolean isForce;
    private String updateContent;
    private String mTitle;
    private void initListener() {
        tvCancel.setOnClickListener(this);
        tvConfirm.setOnClickListener(this);
    }

    public static CustomDialogFragment getInstance(String content,boolean isForce){
        CustomDialogFragment fragment = new CustomDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("updateContent",content);
        bundle.putBoolean("forceUp",isForce);
        fragment.setArguments(bundle);
        return fragment;
    }
    public static CustomDialogFragment getInstance(String content,String title,boolean isForce){
        CustomDialogFragment fragment = new CustomDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("updateContent",content);
        bundle.putBoolean("forceUp",isForce);
        bundle.putString("title",title);
        fragment.setArguments(bundle);
        return fragment;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach: ");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach: ");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        Bundle arguments = getArguments();
        updateContent = arguments.getString("updateContent");
        isForce = arguments.getBoolean("forceUp");
        mTitle = arguments.getString("title");
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Log.d(TAG, "onCreateDialog: ");
        return super.onCreateDialog(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated: ");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
        Dialog dialog = getDialog();
        dialog.setCanceledOnTouchOutside(!isForce);
        if (dialog != null) {
            DisplayMetrics dm = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
            ColorDrawable dw = new ColorDrawable(0x00000000);
            dialog.getWindow().setLayout((int) (dm.widthPixels * 0.75), ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setBackgroundDrawable(dw);
            if (isForce) {
                dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                    @Override
                    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                        if (keyCode == KeyEvent.KEYCODE_BACK) {
                            return true;
                        }
                        return false;
                    }
                });
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState: ");

    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        Log.d(TAG, "onDestroyView: ");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.dialog_version_updapte, container);
        unbinder = ButterKnife.bind(this, view);
        initView();
        initListener();
        return view;


    }

    private void initView() {
        if (!TextUtils.isEmpty(updateContent)){
            String result = formatData(updateContent);
            tvUpdateContent.setText(result);
        }
        if (!TextUtils.isEmpty(mTitle)){
            tvTitle.setText(mTitle);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated: ");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_cancel:
                if (!isForce){
                    dismiss();
                }
                if (dialogButtonClickListener != null){
                    dialogButtonClickListener.cancelButtonClick();
                }
                break;
            case R.id.tv_confirm:
                if (!isForce){
                    dismiss();
                }
                if (dialogButtonClickListener != null){
                    dialogButtonClickListener.confirmButtonClick();
                }
                break;
        }
    }

    public void setDialogButtonClickListener(OnDialogButtonClickListener dialogButtonClickListener) {
        this.dialogButtonClickListener = dialogButtonClickListener;
    }

    public interface OnDialogButtonClickListener {
        void cancelButtonClick();
        void confirmButtonClick();
    }

    private String formatData(String content) {
        String result = "";
        StringBuilder stringBuilder = new StringBuilder();
        if (!TextUtils.isEmpty(content)){
            if (content.contains(";")){
                String[] split = content.split(";");
                for (String temp : split){
                    stringBuilder.append(temp).append("\n");
                }
            }
            result = stringBuilder.toString().trim();
        }
        return result;
    }
}
