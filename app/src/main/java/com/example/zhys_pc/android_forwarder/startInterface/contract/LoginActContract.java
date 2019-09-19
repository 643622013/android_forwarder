package com.example.zhys_pc.android_forwarder.startInterface.contract;

import com.example.zhys_pc.android_forwarder.base.IBaseView;

public interface LoginActContract {

    interface View extends IBaseView {
        void loginSuccess(String token);

        void getNoLoginTokenSuccess(String token);

        void getMsgNum(String num);

        void error(String msg);
    }

    interface Presenter {
        void getVisitorToken();

        void loginUser(String phoneNum, String password);

        void getMsgNum();

    }
}
