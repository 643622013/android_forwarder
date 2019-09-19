package com.example.zhys_pc.android_forwarder.base;

import android.content.Context;
import android.view.View;


import com.cfbx.framework.BaseFragment;
import com.cfbx.framework.rxbus.RxBus;
import com.example.zhys_pc.android_forwarder.MyApplication;
import com.example.zhys_pc.android_forwarder.R;
import com.example.zhys_pc.android_forwarder.customview.requestError.RequestErrorController;
import com.example.zhys_pc.android_forwarder.tools.ToastMessageUtils;


public abstract class MyBaseFragment extends BaseFragment implements IBaseView {

    public MyBaseActivity myBaseActivity;
    public boolean isLocation = false;
    public RequestErrorController requestErrorController;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        myBaseActivity = (MyBaseActivity) getActivity();
        appContext = mActivity.getApplicationContext();
        RxBus.get().register(this);
    }

    /*
     * param view 传入要隐藏的布局
     * 网络异常  以及服务器异常
     * */
    public RequestErrorController.Builder initRequestError(View view) {
        RequestErrorController.Builder builder = new RequestErrorController.Builder(mActivity, view)
                .setErrorImageResoruce(R.drawable.ic_error_icon_noserver)
                .setNetWorkImageResoruce(R.drawable.ic_error_icon_nowifi);
        return builder;

    }


    @Override
    public void setView(View rootView) {
        initTopBar();
        initView(rootView);
    }


    public abstract void initView(View rootView);

    public abstract void initTopBar();

    @Override
    public void unVisible() {

    }

    @Override
    public void showMsg(String msg) {
        ToastMessageUtils.toastSuccess(msg, true);

    }

    @Override
    public void showError(boolean show, Exception e) {
        myBaseActivity.requestErrorController = requestErrorController;
        myBaseActivity.showError(show, e);
    }

    @Override
    public void requestPermissions(MyBaseActivity.OnPermissionGranted onPermissionGranted, String... permission) {
        myBaseActivity.requestPermissions(onPermissionGranted, permission);
    }


    @Override
    public void showLoading() {
        myBaseActivity.showLoading();
    }

    @Override
    public void hideLoading() {
        myBaseActivity.hideLoading();
    }

    /**
     * 重新登录
     *
     * @param dialogShow 是否弹窗提示，建议token失效时提示用户，未登录没有token时不提示
     */
    @Override
    public void reLogin(boolean dialogShow) {
        myBaseActivity.reLogin(dialogShow);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxBus.get().unRegister(this);
    }
}
