package com.cfbx.framework;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jaeger.library.StatusBarUtil;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 71033 on 2017/10/10.
 */

public abstract class BaseFragment extends Fragment {
    protected BaseActivity mActivity;
    protected View mRootView;
    protected boolean mIsVisible;
    /**
     * 是否加载完成
     * 当执行完oncreatview,View的初始化方法后方法后即为true
     */
    protected boolean mIsPrepare;
    protected Context appContext;
    Unbinder unbinder;

    private boolean visibleState = false;
    private boolean unVisibleState = false;
    private boolean startState = false;
    private boolean stopState = false;

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (BaseActivity) getActivity();
        appContext = mActivity.getApplicationContext();
    }

    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(setLayoutResouceId(), container, false);
        return mRootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, mRootView);
        initData(getArguments());
        setView(mRootView);
        mIsPrepare = true;
        onVisibleToUser();
    }

    /**
     * 设置根布局资源id
     *
     * @return
     */
    protected abstract int setLayoutResouceId();

    /**
     * 初始化控件
     *
     * @param rootView
     */
    public abstract void setView(View rootView);

    /**
     * 初始化数据
     *
     * @param arguments 接收到的从其他地方传递过来的参数
     * @author wpf
     * @date 2017/10/10
     */
    public abstract void initData(Bundle arguments);

    /**
     * 懒加载，仅当用户可见切view初始化结束后才会执行
     */
    public abstract void onLazyLoad();

    /**
     * 用户不可见
     */
    public abstract void unVisible();


    @Override
    public void onStart() {
        super.onStart();
        if (visibleState) {
            startState = true;
            onVisibleToUser();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (unVisibleState) {
            stopState = true;
            unVisible();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.mIsVisible = isVisibleToUser;
        if (isVisibleToUser) {
            visibleState = true;
            onVisibleToUser();
        } else {
            unVisibleState = true;
            unVisible();
        }
    }


    /**
     * [页面跳转]
     *
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
        intent.setClass(mActivity, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 用户可见时执行的操作
     */
    protected void onVisibleToUser() {
        if (mIsPrepare && mIsVisible) {
            onLazyLoad();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

//    /**
//     * 设置状态栏字体颜色
//     * Flag只有在使用了FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS
//     * 并且没有使用 FLAG_TRANSLUCENT_STATUS的时候才有效，也就是只有在状态栏全透明的时候才有效。
//     *
//     * @param bDark
//     */
//    public void setStatusBarMode(boolean bDark) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            View decorView = mActivity.getWindow().getDecorView();
//            if (decorView != null) {
//                int vis = decorView.getSystemUiVisibility();
//                if (bDark) {
//                    vis |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
//                } else {
//                    vis &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
//                }
//                decorView.setSystemUiVisibility(vis);
//            }
//        }
//    }

}