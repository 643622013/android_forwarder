package com.cfbx.framework.http.download;

import java.io.File;

/**
 * <p>下载文件回调</p>
 */
public interface DownloadListener {

    //下载成功的回调
    void onDownLoadSuccess(File file);
    //下载失败回调
    void onDownLoadFail(Throwable throwable);
    //下载进度监听
    void onProgress(int progress);
}
