package com.example.zhys_pc.android_forwarder.customview.requestError;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.zhys_pc.android_forwarder.R;

import androidx.annotation.NonNull;


public class RequestErrorController implements RequestErrorInterface {

    private Context context;
    private View loadingTargetView;

    // other controller_request_error
    private int errorImageResoruce;
    private Drawable errorImageDrawable;
    private String errorMessage;


    private int networkImageResoruce;
    private Drawable networkImageDrawable;
    private String networkMessage;


    // listener
    private String networkErrorRetryText;
    private RequestErrorInterface.OnClickListener onNetworkErrorRetryClickListener;
    private String errorRetryText;
    private RequestErrorInterface.OnClickListener onErrorRetryClickListener;

    private LayoutInflater inflater;
    /**
     * {@link #loadingTargetView} 的父布局
     */
    private ViewGroup parentView;
    /**
     * {@link #loadingTargetView} 在父布局中的位置
     */
    private int currentViewIndex;
    /**
     * {@link #loadingTargetView} 的LayoutParams
     */
    private ViewGroup.LayoutParams params;


    private View networkErrorView;
    private View errorView;

    private RequestErrorController(Builder builder) {
        context = builder.context;
        loadingTargetView = builder.loadingTargetView;
        errorImageResoruce = builder.errorImageResoruce;
        errorImageDrawable = builder.errorImageDrawable;
        errorMessage = builder.errorMessage;
        networkMessage = builder.networkMessage;


        networkImageDrawable = builder.networkImageDrawable;
        networkImageResoruce = builder.networkImageResoruce;

        networkErrorRetryText = builder.networkErrorRetryText;
        onNetworkErrorRetryClickListener = builder.onNetworkErrorRetryClickListener;
        errorRetryText = builder.errorRetryText;
        onErrorRetryClickListener = builder.onErrorRetryClickListener;
        if (builder.customNetworkErrorView != null) {
            networkErrorView = builder.customNetworkErrorView;
        }
        if (builder.customErrorView != null) {
            errorView = builder.customErrorView;
        }
        init();
    }

    private void init() {
        inflater = LayoutInflater.from(context);
        params = loadingTargetView.getLayoutParams();
        if (loadingTargetView.getParent() != null) {
            parentView = (ViewGroup) loadingTargetView.getParent();
        } else {
            parentView = (ViewGroup) loadingTargetView.getRootView().findViewById(android.R.id.content);
        }
        int count = parentView.getChildCount();
        for (int i = 0; i < count; i++) {
            if (loadingTargetView == parentView.getChildAt(i)) {
                currentViewIndex = i;
                break;
            }
        }
    }

    /**
     * 切换状态
     *
     * @param view 目标View
     */
    public void showView(View view) {
        // 如果当前状态和要切换的状态相同，则不做处理，反之切换
        if (parentView.getChildAt(currentViewIndex) != view) {
            // 先把view从父布局移除
            ViewGroup viewParent = (ViewGroup) view.getParent();
            if (viewParent != null) {
                viewParent.removeView(view);
            }
            parentView.removeViewAt(currentViewIndex);
            parentView.addView(view, currentViewIndex, params);
        }
    }


    @SuppressLint("InflateParams")
    @Override
    public void showNetworkError() {
        if (networkErrorView != null) {
            showView(networkErrorView);
            return;
        }
        networkErrorView = inflater.inflate(R.layout.controller_request_error, null);
        ImageView ivError = (ImageView) networkErrorView.findViewById(R.id.iv_error);
        if (networkImageResoruce != 0) {
            ivError.setImageResource(networkImageResoruce);
        } else if (networkImageDrawable != null) {
            ivError.setImageDrawable(networkImageDrawable);
        }

        TextView tvNetworkErrorMessage = (TextView) networkErrorView.findViewById(R.id.tv_errorMessage);
        if (!TextUtils.isEmpty(networkMessage)) {
            tvNetworkErrorMessage.setText(networkMessage);
        } else {
            tvNetworkErrorMessage.setText(context.getResources().getString(R.string.network_error_message));
        }

        Button btnRetry = (Button) networkErrorView.findViewById(R.id.btn_retry);

        if (!TextUtils.isEmpty(networkErrorRetryText)) {
            btnRetry.setText(networkErrorRetryText);
        }
        if (onNetworkErrorRetryClickListener != null) {
            btnRetry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onNetworkErrorRetryClickListener.onClick();
                }
            });
        }

        showView(networkErrorView);
    }

    @SuppressLint("InflateParams")
    @Override
    public void showError() {
        if (errorView != null) {
            showView(errorView);
            return;
        }
        errorView = inflater.inflate(R.layout.controller_request_error, null);
        ImageView ivError = (ImageView) errorView.findViewById(R.id.iv_error);
        TextView tvErrorMessage = (TextView) errorView.findViewById(R.id.tv_errorMessage);
        Button btnRetry = (Button) errorView.findViewById(R.id.btn_retry);

        if (errorImageResoruce != 0) {
            ivError.setImageResource(errorImageResoruce);
        } else if (errorImageDrawable != null) {
            ivError.setImageDrawable(errorImageDrawable);
        }

        if (!TextUtils.isEmpty(errorMessage)) {
            tvErrorMessage.setText(errorMessage);
        } else {
            tvErrorMessage.setText(context.getResources().getString(R.string.error_message));
        }

        if (!TextUtils.isEmpty(errorRetryText)) {
            btnRetry.setText(errorRetryText);
        }
        if (onErrorRetryClickListener != null) {
            btnRetry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onErrorRetryClickListener.onClick();
                }
            });
        }

        showView(errorView);
    }


    public static class Builder {

        private Context context;
        private View loadingTargetView;
        // network qlk_loading_result
        private View customNetworkErrorView;
        private int networkImageResoruce;
        private Drawable networkImageDrawable;
        private String networkMessage;

        // normal qlk_loading_result
        private int errorImageResoruce;
        private Drawable errorImageDrawable;
        private String errorMessage;
        private View customErrorView;


        // listener
        private String networkErrorRetryText;
        private RequestErrorInterface.OnClickListener onNetworkErrorRetryClickListener;
        private String errorRetryText;
        private RequestErrorInterface.OnClickListener onErrorRetryClickListener;


        public Builder(@NonNull Context context, @NonNull View loadingTargetView) {
            this.context = context;
            this.loadingTargetView = loadingTargetView;
        }

        /**
         * 自定义networkErrorView
         *
         * @param networkErrorView view for networkError
         * @return this Builder
         */
        public Builder setNetworkErrorView(View networkErrorView) {
            this.customNetworkErrorView = networkErrorView;
            return this;
        }

        public Builder setNetWorkImageResoruce(int networkImageResoruce) {
            this.networkImageResoruce = networkImageResoruce;
            return this;
        }

        public Builder setNetWorkImageDrawable(Drawable networkImageDrawable) {
            this.networkImageDrawable = networkImageDrawable;
            return this;
        }


        public Builder setNetworkMessage(String networkMessage) {
            this.networkMessage = networkMessage;
            return this;
        }
        //error

        public Builder setErrorImageResoruce(int errorImageResoruce) {
            this.errorImageResoruce = errorImageResoruce;
            return this;
        }

        public Builder setErrorImageDrawable(Drawable errorImageDrawable) {
            this.errorImageDrawable = errorImageDrawable;
            return this;
        }

        public Builder setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
            return this;
        }

        /**
         * 自定义errorView
         *
         * @param errorView view for controller_request_error
         * @return this Builder
         */
        public Builder setErrorView(View errorView) {
            this.customErrorView = errorView;
            return this;
        }


        public Builder setOnNetworkErrorRetryClickListener(RequestErrorInterface.OnClickListener listener) {
            this.onNetworkErrorRetryClickListener = listener;
            return this;
        }

        public Builder setOnNetworkErrorRetryClickListener(String networkErrorRetryText, RequestErrorInterface.OnClickListener listener) {
            this.networkErrorRetryText = networkErrorRetryText;
            this.onNetworkErrorRetryClickListener = listener;
            return this;
        }

        public Builder setOnErrorRetryClickListener(RequestErrorInterface.OnClickListener listener) {
            this.onErrorRetryClickListener = listener;
            return this;
        }

        public Builder setOnErrorRetryClickListener(String errorRetryText, RequestErrorInterface.OnClickListener listener) {
            this.errorRetryText = errorRetryText;
            this.onErrorRetryClickListener = listener;
            return this;
        }


        public RequestErrorController build() {
            return new RequestErrorController(this);
        }

    }
}
