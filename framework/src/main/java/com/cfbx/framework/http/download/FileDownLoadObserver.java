package com.cfbx.framework.http.download;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public abstract class FileDownLoadObserver<File> implements Observer<File> {
    @Override
    public void onNext(File file) {
        onDownLoadSuccess(file);
    }

    @Override
    public void onError(Throwable e) {
        onDownLoadFail(e);
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    //下载成功的回调
    public abstract void onDownLoadSuccess(File file);
    //下载失败回调
    public abstract void onDownLoadFail(Throwable throwable);
    //下载进度监听
    public abstract void onProgress(int progress);


}
