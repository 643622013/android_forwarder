package com.example.zhys_pc.android_forwarder.http.consumer;

import com.cfbx.framework.exception.ExceptionEngine;

import io.reactivex.functions.Consumer;

/**
 * Created by liujindong on 2018/7/14.
 */

public abstract class ErrorConsumer implements Consumer<Throwable>, ISubErrorConsumer {

    @Override
    public void accept(Throwable throwable) throws Exception {
        error(ExceptionEngine.handleException(throwable).getMessage());
    }

}
