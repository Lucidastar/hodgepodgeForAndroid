package com.mine.lucidastarutils.download;

import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;



/**
 * Created by hanxiaoxing on 17/1/16.
 */

public class DownLoadManager {

   /* private static volatile DownLoadManager INSTANCE;
    private DownLoadCallBack mDownLoadCallBack;
    private static String mFiledPath = Environment.getExternalStorageDirectory().getAbsolutePath();
    private static String mFiledName = "test.apk";
    private static String mUrl;
    OkHttpClient client;
    Call call;

    private DownLoadManager(String filedPath, String filedName, String url, DownLoadCallBack callBack) {
        this.mDownLoadCallBack = callBack;
        this.mFiledPath = filedPath;
        this.mFiledName = filedName;
        this.mUrl = url;
        client = new OkHttpClient();

    }

    public static DownLoadManager getInstance(String filedPath, String filedName, String url, DownLoadCallBack callBack) {
        if (INSTANCE == null) {
            synchronized (DownLoadManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DownLoadManager(filedPath, filedName, url, callBack);
                }
            }
        }
        return INSTANCE;
    }


    public static DownLoadManager getInstance(String url,DownLoadCallBack callBack) {
        return getInstance(mFiledPath, mFiledName, url, callBack);
    }


    public void startDownLoad() {
        RequestBody formBody = null;
        Request request = new Request.Builder().url(mUrl).build();
        call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mDownLoadCallBack.failure();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream is = null;
                byte[] buf = new byte[2048];
                int len = 0;
                FileOutputStream fos = null;
                try {
                    is = response.body().byteStream();
                    final long total = response.body().contentLength();

                    long sum = 0;

                    File dir = new File(mFiledPath);
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }
                    File file = new File(dir, mFiledName);
                    if (file.exists()) {
                        file.delete();
                    }
                    fos = new FileOutputStream(file);
                    while ((len = is.read(buf)) != -1) {
                        sum += len;
                        fos.write(buf, 0, len);
                        final long finalSum = sum;
                        mDownLoadCallBack.inProgress(finalSum * 1.0f / total, total);
                    }
                    fos.flush();
                    mDownLoadCallBack.success();
                } catch (Exception e){
                    mDownLoadCallBack.failure();
                }
                finally {
                    try {
                        response.body().close();
                        if (is != null) is.close();
                    } catch (IOException e) {
                    }
                    try {
                        if (fos != null) fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

            }
        });

    }


    //取消下载
    public void cancelDownLoad(){
        if (call.isExecuted()){
            call.cancel();
            if (mDownLoadCallBack != null){
                mDownLoadCallBack.cancelDownLoad(mFiledPath);
            }
        }
    }
*/

}
