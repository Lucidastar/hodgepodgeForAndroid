package com.lucidastar.hodgepodge.ui.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Toast;

import com.lucidastar.hodgepodge.R;
import com.lucidastar.hodgepodge.ui.activity.custom.CustomActivity;
import com.lucidastar.hodgepodge.ui.activity.otherfeature.TestBitmapActivity;
import com.lucidastar.hodgepodge.ui.activity.otherfeature.animation.AnimationActivity;
import com.lucidastar.hodgepodge.ui.activity.otherfeature.handler.TestHandlerActivity;
import com.lucidastar.hodgepodge.ui.activity.otherfeature.saidl.StudentAidlActivity;
import com.lucidastar.hodgepodge.ui.base.BaseFragment;
import com.lucidastar.hodgepodge.view.CustomDialogFragment;
import com.mine.lucidastarutils.utils.MemoryManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class OtherFeatureFragment extends BaseFragment {


    public OtherFeatureFragment() {
        // Required empty public constructor
    }


    @Override
    protected void setUpView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_other_feature;
    }

    @OnClick({R.id.btn_study_handler, R.id.btn_study_other,R.id.btn_study_bitmap,R.id.btn_study_aidl,R.id.btn_study_fragment_dialog,R.id.btn_study_custom_view,R.id.btn_study_animation,R.id.btn_install_apk})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_study_handler:
                startActivity(new Intent(getActivity(), TestHandlerActivity.class));
                break;
            case R.id.btn_study_other:

                break;
            case R.id.btn_study_bitmap:
                startActivity(new Intent(getActivity(), TestBitmapActivity.class));
                break;

            case R.id.btn_study_aidl:
                startActivity(new Intent(getActivity(), StudentAidlActivity.class));
                break;

            case R.id.btn_study_fragment_dialog:
                String content = "1、界面优化;2、加载数据的bug";
                 CustomDialogFragment customDialogFragment = CustomDialogFragment.getInstance(content,false);
//                customDialogFragment.setRetainInstance(true);
                customDialogFragment.setDialogButtonClickListener(new CustomDialogFragment.OnDialogButtonClickListener() {
                    @Override
                    public void cancelButtonClick() {
                        Toast.makeText(getActivity(),"取消",Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void confirmButtonClick() {
                        Toast.makeText(getActivity(),"开始下载",Toast.LENGTH_SHORT).show();
                    }
                });
                customDialogFragment.show(getFragmentManager(),"customDialog");
                break;
            case R.id.btn_study_custom_view:
                startActivity(new Intent(getActivity(), CustomActivity.class));
                break;

            case R.id.btn_study_animation:
                startActivity(new Intent(getActivity(), AnimationActivity.class));
                break;
                //安装apk
            case R.id.btn_install_apk:
                /**
                 * 获取apk（保存的路径）
                 * 安装apk
                 */
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {

                            if (MemoryManager.externalMemoryAvailable()){
                                String path = MemoryManager.externalMemoryAbsolutePath()+"/myapk";
                                InputStream open = getActivity().getAssets().open("apks/test.apk");
//                        boolean isWriteSuccess = FileIOUtils.writeFileFromIS(path,open);
//                                File file = new File(path);
//                                if (!file.exists()){
//                                    file.mkdirs();
//                                }
//                                copyFilesFassets(getActivity(),"test.apk",path);
                                copyAssetsToDst(getActivity(),"apks",path);
//                        if (isWriteSuccess){
////                            AppUtils.installApp(path+"test.apk","");
//                        }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

                break;
        }
    }
    public void copyFilesFassets(Context context, String oldPath, String newPath) {
        try {
            String fileNames[] = context.getAssets().list(oldPath);//获取assets目录下的所有文件及目录名
            if (fileNames.length > 0) {//如果是目录
                File file = new File(newPath);
                file.mkdirs();//如果文件夹不存在，则递归
                for (String fileName : fileNames) {
                    copyFilesFassets(context,oldPath + "/" + fileName,newPath+"/"+fileName);
                }
            } else {//如果是文件
                InputStream is = context.getAssets().open(oldPath);
                FileOutputStream fos = new FileOutputStream("apks/test.apk");
                byte[] buffer = new byte[1024];
                int byteCount=0;
                while((byteCount=is.read(buffer))!=-1) {//循环从输入流读取 buffer字节
                    fos.write(buffer, 0, byteCount);//将读取的输入流写入到输出流
                }
                fos.flush();//刷新缓冲区
                is.close();
                fos.close();
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            //如果捕捉到错误则通知UI线程

        }
    }
    private void copyAssetsToDst(Context context, String srcPath, String dstPath) {
        try {
            String fileNames[] = context.getAssets().list(srcPath);
            if (fileNames.length > 0) {
                File file = new File(dstPath);
                if (!file.exists()) file.mkdirs();
                for (String fileName : fileNames) {
                    if (!srcPath.equals("")) { // assets 文件夹下的目录
                        copyAssetsToDst(context, srcPath + File.separator + fileName, dstPath + File.separator + fileName);
                    } else { // assets 文件夹
                        copyAssetsToDst(context, fileName, dstPath + File.separator + fileName);
                    }
                }
            } else {
                File outFile = new File(dstPath);
                InputStream is = context.getAssets().open(srcPath);
                FileOutputStream fos = new FileOutputStream(outFile);
                byte[] buffer = new byte[1024];
                int byteCount;
                while ((byteCount = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, byteCount);
                }
                fos.flush();
                is.close();
                fos.close();
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
