package com.example.zhys_pc.android_forwarder.customview;

import android.app.Activity;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cfbx.framework.util.DeviceUtils;
import com.example.zhys_pc.android_forwarder.MyApplication;
import com.example.zhys_pc.android_forwarder.R;

import androidx.annotation.ColorInt;

public class ToolBarBuilder {
    /**
     * 通用bar
     */

    private RelativeLayout mNavicationBar;
    private View mStatusBar;

    private TextView mTvTitle;

    private RelativeLayout mLeftViewContainer;
    private ImageView left_image;


    private LinearLayout mRightViewContainer;
    private ImageView right_image;
    private TextView right_tv;


    private Activity mActivity;
    private View mRootView;
    private String mTitle;

    public ToolBarBuilder(Activity activity) {
        this.mActivity = activity;

        mNavicationBar = mActivity.findViewById(R.id.tool_bar);
        mStatusBar = mActivity.findViewById(R.id.status_bar);

        mTvTitle = mActivity.findViewById(R.id.tv_title_bar_title);

        mLeftViewContainer = mActivity.findViewById(R.id.lib_base_header_bar_left);
        left_image = mActivity.findViewById(R.id.iv_title_bar_left);

        mRightViewContainer = mActivity.findViewById(R.id.lib_base_header_bar_right);
        right_image = mActivity.findViewById(R.id.right_image);
        right_tv = mActivity.findViewById(R.id.right_tv);
        showStatusBar(true);
    }

    public ToolBarBuilder(View rootView) {
        this.mRootView = rootView;
        mNavicationBar = mRootView.findViewById(R.id.tool_bar);
        mStatusBar = mRootView.findViewById(R.id.status_bar);

        mTvTitle = mRootView.findViewById(R.id.tv_title_bar_title);

        mLeftViewContainer = mRootView.findViewById(R.id.lib_base_header_bar_left);
        left_image = mRootView.findViewById(R.id.iv_title_bar_left);

        mRightViewContainer = mRootView.findViewById(R.id.lib_base_header_bar_right);
        right_image = mRootView.findViewById(R.id.right_image);
        right_tv = mRootView.findViewById(R.id.right_tv);
    }

    public ToolBarBuilder showStatusBar(boolean show){
        if(show){
            mStatusBar.setVisibility(View.VISIBLE);
        }else{
            mStatusBar.setVisibility(View.GONE);
        }
        return this;
    }

    //设置背景图片
    public ToolBarBuilder setBackgroundRes(int res) {
        mNavicationBar.setBackgroundResource(res);
        return this;
    }

    /**
     * 设置背景颜色
     * 状态栏与导航栏一同设置
     * @param color
     * @param alpha 0 - 1
     * @return
     */
    public ToolBarBuilder setBackgroundColor(@ColorInt int color, float alpha) {
        float a = alpha * 255;
        if(a<0){
            a = 0;
        }
        if(a>255){
            a=255;
        }
        mStatusBar.setBackgroundColor(color);
        mStatusBar.getBackground().setAlpha((int) a);
        mNavicationBar.setBackgroundColor(color);
        mNavicationBar.getBackground().setAlpha((int) a);
        return this;
    }

    /**
     * 设置背景颜色
     * 状态栏与导航栏一同设置
     * @param color
     * @param alpha 0 - 255
     * @return
     */
    public ToolBarBuilder setBackgroundColor(@ColorInt int color, int alpha){
        if(alpha<0){
            alpha = 0;
        }
        if(alpha>255){
            alpha=255;
        }
        mStatusBar.setBackgroundColor(color);
        mStatusBar.getBackground().setAlpha(alpha);
        mNavicationBar.setBackgroundColor(color);
        mNavicationBar.getBackground().setAlpha(alpha);
        return this;
    }

    /**
     * 设置状态栏背景颜色
     *
     * @param color
     * @return
     */
    public ToolBarBuilder setStatusBarColor(@ColorInt int color, float alpha) {
        mStatusBar.setBackgroundColor(color);
        float a = alpha * 255;
        mStatusBar.getBackground().setAlpha((int) a);
        return this;
    }

    /**
     * 设置导航栏背景颜色
     *
     * @param color
     * @return
     */
    public ToolBarBuilder setNavicationBarColor(@ColorInt int color, float alpha) {
        mNavicationBar.setBackgroundColor(color);
        float a = alpha * 255;
        mNavicationBar.getBackground().setAlpha((int) a);
        return this;
    }


    /**
     * 设置标题
     * @param title
     * @return
     */
    public ToolBarBuilder setTitle(String title) {
        mTitle = title;
        mTvTitle.setText(title);
        return this;
    }

    /**
     * 设置标题颜色
     * @param color
     * @return
     */
    public ToolBarBuilder setTitleColor(int  color) {
        mTvTitle.setTextColor(color);
        return this;
    }

    /**
     * 设置左侧第一张图片
     * @param res
     * @return
     */
    public ToolBarBuilder setLeftImageView(Integer res){
        mLeftViewContainer.setVisibility(View.VISIBLE);
        Glide.with(MyApplication.getContext()).load(res).into(left_image);
        return this;
    }


    /**
     * 设置右侧第一张图片
     * @param resourceId
     * @return
     */
    public ToolBarBuilder setRight_image(Integer resourceId) {
        mRightViewContainer.setVisibility(View.VISIBLE);
        right_image.setVisibility(View.VISIBLE);
        Glide.with(MyApplication.getContext()).load(resourceId).into(right_image);
        return this;
    }






    public TextView getRight_tv() {
        return right_tv;
    }

    /**
     * 设置右侧textView
     * @param text
     * @return
     */
    public ToolBarBuilder setRightText(String text){
        mRightViewContainer.setVisibility(View.VISIBLE);
        right_tv.setVisibility(View.VISIBLE);
        right_tv.setText(text);
        return this;
    }
    /**
     * 设置右侧textView颜色
     * @param color
     * @return
     */
    public ToolBarBuilder setRightTextColor(int color){
        mRightViewContainer.setVisibility(View.VISIBLE);
        right_tv.setVisibility(View.VISIBLE);
        right_tv.setTextColor(color);
        return this;
    }
    /**
     * 设置右侧textView的大小
     * @param size
     * @return
     */
    public ToolBarBuilder setRightTextSize(float size){
        mRightViewContainer.setVisibility(View.VISIBLE);
        right_tv.setVisibility(View.VISIBLE);
        right_tv.setTextSize(TypedValue.COMPLEX_UNIT_SP,size);
        return this;
    }

    /**
     * 设置右侧textView的右边距
     * @param right
     * @return
     */
    public ToolBarBuilder setRightTextMarginRight(int right){
        //这里其父布局已经设置android:layout_marginRight="12dp"，所以要减去12dp
        mRightViewContainer.setVisibility(View.VISIBLE);
        right_tv.setVisibility(View.VISIBLE);
        if( DeviceUtils.dip2px(MyApplication.getContext(), right)-DeviceUtils.dip2px(MyApplication.getContext(), 12)>0){
            right = DeviceUtils.dip2px(MyApplication.getContext(), right)-DeviceUtils.dip2px(MyApplication.getContext(), 12);
        }else {
            right = DeviceUtils.dip2px(MyApplication.getContext(), right);
        }
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(right_tv.getLayoutParams());
        layoutParams.setMargins(0, 0, right, 0);
        right_tv.setLayoutParams(layoutParams);
        return this;
    }

    /**
     * 左侧第一张图片点击事件
     * @param l
     * @return
     */
    public ToolBarBuilder setLeftOnClickListener(View.OnClickListener l) {
        mLeftViewContainer.setOnClickListener(l);
        return this;
    }




    /**
     * 设置右侧第一张图片点击事件
     * @param l
     * @return
     */
    public ToolBarBuilder setRightOnClickListener(View.OnClickListener l) {
        mRightViewContainer.setOnClickListener(l);
        return this;
    }





    /**
     * 设置右侧text点击事件
     * @param l
     * @return
     */
    public ToolBarBuilder setRightTvOnClickListener(View.OnClickListener l){
        right_tv.setOnClickListener(l);
        return this;
    }


    /**
     * 设置右侧第一张图片宽高
     * @param height
     * @param width
     * @return
     */
    public ToolBarBuilder setRight_imageHeightWidth( int height, int width) {
        height = DeviceUtils.dip2px(MyApplication.getContext(), height);
        width = DeviceUtils.dip2px(MyApplication.getContext(), width);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(right_image.getLayoutParams());
        layoutParams.height = height;// 设置图片的高度
        layoutParams.width = width; // 设置图片的宽度
        right_image.setLayoutParams(layoutParams);
        return this;
    }
}
