package com.example.zhys_pc.android_forwarder.customview.requestError;


public interface RequestErrorInterface {

    //无网络
    void showNetworkError();
    //服务器异常
    void showError();




    interface OnClickListener {
        void onClick();
    }
}
