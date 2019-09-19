package com.example.zhys_pc.android_forwarder.base;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;


import com.cfbx.framework.BaseActivity;
import com.cfbx.framework.exception.ApiException;
import com.cfbx.framework.exception.MyException;
import com.cfbx.framework.rxbus.RxBus;
import com.cfbx.framework.util.LogUtils;
import com.example.zhys_pc.android_forwarder.MyApplication;
import com.example.zhys_pc.android_forwarder.R;
import com.example.zhys_pc.android_forwarder.customview.requestError.RequestErrorController;
import com.example.zhys_pc.android_forwarder.dialog.CustomProgressDialog;
import com.example.zhys_pc.android_forwarder.mainInterface.MainActivity;
import com.example.zhys_pc.android_forwarder.tools.FinishActivityManager;
import com.example.zhys_pc.android_forwarder.tools.ToastMessageUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AlertDialog;

public abstract class MyBaseActivity extends BaseActivity implements IBaseView {
    private CustomProgressDialog dialog;
    public RequestErrorController requestErrorController;

    @Override
    public void setView(Bundle savedInstanceState) {
        initView(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//设定为竖屏
        //存储activity
        FinishActivityManager.getManager().addActivity(this);
        RxBus.get().register(this);
        initTopBar();
        dialog = new CustomProgressDialog(this, "加载中", R.drawable.frame);

    }

    /*
     * param view 传入要隐藏的布局
     * 网络异常  以及服务器异常
     * */
    public RequestErrorController.Builder initRequestError(View view) {
        RequestErrorController.Builder builder = new RequestErrorController.Builder(this, view)
                .setErrorImageResoruce(R.drawable.ic_error_icon_noserver)
                .setNetWorkImageResoruce(R.drawable.ic_error_icon_nowifi);
        return builder;

    }

    /**
     * [初始化页面]
     */
    public abstract void initView(Bundle savedInstanceState);

    /**
     * [初始化topbar]
     */
    public abstract void initTopBar();

    @Override
    public void showMsg(String msg) {
        ToastMessageUtils.toastSuccess(msg, true);

    }


    @Override
    public void showError(boolean show, Exception e) {
        hideLoading();
        LogUtils.e(e);
        if (show) {
            String tips = "";
            String btnStr = "我知道了";
            if (e instanceof ApiException) {
                ApiException apiExc = (ApiException) e;
                tips = apiExc.getMsg();

                //显示服务器异常
                if (null != requestErrorController) {
                    requestErrorController.showError();
                }
            } else if (e instanceof MyException) {
                MyException myExc = (MyException) e;
                switch (myExc.getCode()) {
                    case MyException.HTTP_ERROR:
                        tips = "网络异常，请稍后重试";
                        break;
                    case MyException.ANALYTIC_DATA_ERROR:
                        tips = "网络异常，请稍后重试";
                        break;
                    case MyException.CONNECT_ERROR:
                        tips = "网络异常，请稍后重试";
                        break;
                    case MyException.TIMEOUT_ERROR:
                        tips = "网络异常，请稍后重试";
                        break;
                    case MyException.UNKNOWN_ERROR:
                        tips = "网络异常，请稍后重试";
                        break;
                }
                //显示网络异常
                if (null != requestErrorController) {
                    requestErrorController.showNetworkError();
                }
            }
            ToastMessageUtils.toastError(tips, true);
        }

    }

    private List<Integer> permissionStatus = new ArrayList<>();
    private int index = 0;

    /**
     * 请求权限
     */
    @Override
    public void requestPermissions(final OnPermissionGranted onPermissionGranted, final String... permission) {
        if (index < permission.length) {
            reqeustPermission(new requestPermissionListener() {
                @Override
                public void onNext(String name, int state) {
                    permissionStatus.add(state);
                    switch (state) {
                        case PERMISSION_GRANTED:
                            index++;
                            requestPermissions(onPermissionGranted, permission);
                            break;
                        case PERMISSION_REFUSE_SHOWREQUEST:
                            //	通过AlertDialog.Builder这个类来实例化我们的一个AlertDialog的对象
                            AlertDialog.Builder builder = new AlertDialog.Builder(appContext);
                            // 设置Title的内容
                            builder.setTitle("弹出警告框");
                            // 设置Content来显示一个信息
                            builder.setMessage(appContext.getResources().getString(R.string.permission_tip));
                            // 设置一个PositiveButton
                            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    requestPermissions(onPermissionGranted, permission);
                                }
                            });
                            // 显示出该对话框
                            builder.show();


                            break;
                        case PERMISSION_REFUSE:
                            //	通过AlertDialog.Builder这个类来实例化我们的一个AlertDialog的对象
                            AlertDialog.Builder builder2 = new AlertDialog.Builder(appContext);
                            // 设置Title的内容
                            builder2.setTitle("弹出警告框");
                            // 设置Content来显示一个信息
                            builder2.setMessage(appContext.getResources().getString(R.string.permission_tip));
                            // 设置一个PositiveButton
                            builder2.setPositiveButton("去设置", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    index = 0;
                                    permissionStatus.clear();
                                    toSelfSetting();
                                }
                            });
                            // 显示出该对话框
                            builder2.show();

                            break;
                    }

                }

                @Override
                public void onComplet() {
                    //禁止
                }
            }, permission[index]);
        } else {
            index = 0;
            if (!permissionStatus.contains(2) && !permissionStatus.contains(3)) {
                onPermissionGranted.onAllGranted();
            }
            permissionStatus.clear();
        }

    }

    //跳转到设置页面
    public static void toSelfSetting() {
        Intent mIntent = new Intent();
        mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 9) {
            mIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            mIntent.setData(Uri.fromParts("package", MyApplication.getContext().getPackageName(), null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            mIntent.setAction(Intent.ACTION_VIEW);
            mIntent.setClassName("com.android.settings", "com.android.setting.InstalledAppDetails");
            mIntent.putExtra("com.android.settings.ApplicationPkgName", MyApplication.getContext().getPackageName());
        }
        MyApplication.getContext().startActivity(mIntent);
    }

    public interface OnPermissionGranted {
        void onAllGranted();
    }


    @Override
    public void showLoading() {
        if (null != dialog) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            } else {
                dialog.show();
            }
        }
    }

    @Override
    public void hideLoading() {
        if (null != dialog) {
            dialog.dismiss();
        }
    }


    /**
     * 清空token,重新登录
     */
    /**
     * 是否显示弹窗提示
     *
     * @param dialogShow
     */
    @Override
    public void reLogin(boolean dialogShow) {
        hideLoading();
        removeToken();
        if (dialogShow) {
//            showDialog(getString(R.string.tuichu), "好的", new SimpleDialog.OnConfirmListener() {
//                @Override
//                public void onClick() {
//                    startActivity(SendVerificationCodeActivity.class);
//                }
//            });
        } else {
            //销毁所有activity
            FinishActivityManager.getManager().finishAllActivity();
            startActivity(MainActivity.class);
        }
    }


    /**
     * 清空token
     *
     * @return
     */
    private void removeToken() {
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.get().unRegister(this);
        //销毁activity
        FinishActivityManager.getManager().finishActivity(this);

    }


}
