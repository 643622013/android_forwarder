package com.cfbx.framework;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.cfbx.framework.util.AppUtils;
import com.cfbx.framework.util.LogUtils;
import com.jaeger.library.StatusBarUtil;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.Stack;

import androidx.annotation.IntDef;
import androidx.fragment.app.FragmentActivity;
import butterknife.ButterKnife;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Created by 71033 on 2017/10/9.
 */


public abstract class BaseActivity extends FragmentActivity {
    public static final int PERMISSION_GRANTED = 1;
    public static final int PERMISSION_REFUSE_SHOWREQUEST = 2;
    public static final int PERMISSION_REFUSE = 3;

    /**
     * 是否沉浸状态栏
     **/
    private boolean isSetStatusBar = true;
    /**
     * 是否允许全屏
     **/
    private boolean mAllowFullScreen = false;
    /**
     * 状态栏字体深色模式
     */
    public boolean isDarkStatusBar = false;
    /**
     * antivity栈
     **/
    private Stack<Activity> stack;

    protected RxPermissions rxPermissions;

    protected Context appContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            Bundle bundle = getIntent().getExtras();
            initParams(bundle);
            if (mAllowFullScreen) {
                this.getWindow().setFlags(
                        WindowManager.LayoutParams.FLAG_FULLSCREEN,
                        WindowManager.LayoutParams.FLAG_FULLSCREEN);
                requestWindowFeature(Window.FEATURE_NO_TITLE);
            }
            if (isSetStatusBar) {
                setStatusBar();
            }
            appContext = getApplicationContext();
            super.setContentView(bindLayout());
            setStatusBarMode(isDarkStatusBar);
            ButterKnife.bind(this);
            setView(savedInstanceState);
            doBusiness(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void setStatusBar() {
        StatusBarUtil.setTranslucentForImageViewInFragment(this, null);
    }




    /**
     * 设置状态栏字体颜色
     * Flag只有在使用了FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS
     * 并且没有使用 FLAG_TRANSLUCENT_STATUS的时候才有效，也就是只有在状态栏全透明的时候才有效。
     *
     * @param bDark
     */
    public void setStatusBarMode(boolean bDark) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decorView = getWindow().getDecorView();
            if (decorView != null) {
                int vis = decorView.getSystemUiVisibility();
                if (bDark) {
                    vis |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                } else {
                    vis &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                }
                decorView.setSystemUiVisibility(vis);
            }
        }
    }

    /**
     * [绑定布局]
     *
     * @return
     */
    public abstract int bindLayout();

    /**
     * [数据传递]
     * @param bundle
     */
    public abstract void initParams(Bundle bundle);
    /**
     * [初始化页面]
     */
    public abstract void setView(Bundle savedInstanceState);

    /**
     * [业务操作]
     * @param mContext
     */
    public abstract void doBusiness(Context mContext);

    /**
     * [页面跳转]
     * @param clz
     */
    public void startActivity(Class<?> clz) {
        startActivity(clz, null);
    }

    /**
     * [携带数据的页面跳转]
     *
     * @param clz
     * @param bundle
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * [含有Bundle通过Class打开编辑界面]
     *
     * @param cls
     * @param bundle
     * @param requestCode
     */
    public void startActivityForResult(Class<?> cls, Bundle bundle,
                                       int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    /**
     * 获取APP版本号
     *
     * @return
     */
    public String getVersionName() {
        return AppUtils.getAppVersionName();
    }

    protected static void showMessageOKCancel(final Activity context, String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(context)
                .setMessage(message)
                .setPositiveButton("是", okListener)
                .setNegativeButton("否", null)
                .create()
                .show();
    }


    /**
     * 请求权限
     *
     * @param listener
     * @param permission
     */
    @SuppressLint("CheckResult")
    public void reqeustPermission(final requestPermissionListener listener, final String... permission) {
        if (rxPermissions == null) {
            rxPermissions = new RxPermissions(this);
        }
        rxPermissions
                .requestEach(permission)
                .subscribe(
                        new Consumer<Permission>() {
                            @Override
                            public void accept(Permission permission) {
                                if (permission.granted) {
                                    //授予权限
                                    listener.onNext(permission.name, PERMISSION_GRANTED);
                                } else if (permission.shouldShowRequestPermissionRationale) {
                                    // 拒绝权限
                                    listener.onNext(permission.name, PERMISSION_REFUSE_SHOWREQUEST);
                                } else {
                                    // 拒绝权限并且不在询问
                                    listener.onNext(permission.name, PERMISSION_REFUSE);
                                }
                            }
                        },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable t) {
                                LogUtils.e((Exception) t);
                            }
                        },
                        new Action() {
                            @Override
                            public void run() {
                                listener.onComplet();
                            }
                        }
                );
    }


    /**
     * 请求权限回调
     */
    public interface requestPermissionListener {
        void onNext(String name, @PermissionState int state);

        void onComplet();
    }

    @IntDef({PERMISSION_GRANTED, PERMISSION_REFUSE_SHOWREQUEST, PERMISSION_REFUSE})
    public @interface PermissionState {
    }

}