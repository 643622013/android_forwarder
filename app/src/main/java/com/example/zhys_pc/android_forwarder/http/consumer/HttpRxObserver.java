package com.example.zhys_pc.android_forwarder.http.consumer;


import com.cfbx.framework.ApiRespons;
import com.cfbx.framework.exception.ApiException;
import com.cfbx.framework.exception.ExceptionEngine;
import com.cfbx.framework.exception.MyException;
import com.example.zhys_pc.android_forwarder.MyApplication;
import com.example.zhys_pc.android_forwarder.tools.ToastMessageUtils;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by luyang on 2017/11/13.
 */

public abstract class HttpRxObserver<T> extends DisposableObserver<ApiRespons<T>> {

    /**
     * @desc try  catch 处理类似对象==null的空指针异常
     * @author lilei
     * create at 2019/5/24 14:32
     */

    @Override
    public void onNext(ApiRespons<T> tApiRespons) {
        try {
            if (tApiRespons.getResult().isSuccess()) {
                T result = (T) tApiRespons.getResult().getData();
                onHandleSuccess(result);
            } else if (tApiRespons.getResult().isTokenException()) {// token 异常
                ToastMessageUtils.toastSuccess("为了您的账号安全，请重新登录", true);
                onTokenClose();
            } else if (tApiRespons.getResult().isTokenInvoid()) {
                ToastMessageUtils.toastSuccess("为了您的账号安全，请重新登录", true);
                onTokenClose();
            } else {
                fail(tApiRespons.getMsg());
            }
        } catch (Exception e) {
            Exception exception = ExceptionEngine.handleException(e);
            fail(exception.getMessage());
        }

    }

    @Override
    public void onError(Throwable e) {

        Exception exception = ExceptionEngine.handleException(e);
        /**
         * 异常处理第二层
         */
        if (exception instanceof ApiException) {
            int code = ((ApiException) exception).getCode();

            if (code == 400 || code == 403100 || code == 403101) {//token 异常情况待处理
                onTokenClose();
            } else {
                onServerError((ApiException) exception);
            }
        } else if (exception instanceof MyException) {
            onHttpError((MyException) exception);
        }

    }


    @Override
    public void onComplete() {

    }

    protected abstract void onHandleSuccess(T result);

    protected abstract void onServerError(ApiException e);

    protected abstract void onHttpError(MyException e);

    protected abstract void onTokenClose();

    protected abstract void fail(String failMsg);


}
