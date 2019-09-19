package com.example.zhys_pc.android_forwarder.base;


import com.cfbx.framework.BaseView;


public interface IBaseView extends BaseView {



     void showLoading();

     void hideLoading();

     void showMsg(String msg);

     void showError(boolean show, Exception erro);

     void reLogin(boolean showDialog);

     void requestPermissions(MyBaseActivity.OnPermissionGranted onPermissionGranted, String... permission);
}
