package com.mine.lucidastarutils.download;

/**
 * Created by hanxiaoxing on 17/1/16.
 */

public interface DownLoadCallBack {
    void inProgress(float progress, long total);
    void failure();
    void success();
    void cancelDownLoad(String cachePath);
}
