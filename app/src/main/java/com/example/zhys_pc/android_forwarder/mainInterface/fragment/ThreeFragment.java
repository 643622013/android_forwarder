package com.example.zhys_pc.android_forwarder.mainInterface.fragment;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.model.LottieComposition;
import com.example.zhys_pc.android_forwarder.MyApplication;
import com.example.zhys_pc.android_forwarder.R;
import com.example.zhys_pc.android_forwarder.base.MyBaseFragment;
import com.example.zhys_pc.android_forwarder.customview.ToolBarBuilder;

import butterknife.BindView;

public class ThreeFragment extends MyBaseFragment {

    @BindView(R.id.animation_view_click)
    LottieAnimationView animation_view_click;
    @BindView(R.id.button1)
    Button button1;
    @BindView(R.id.button2)
    Button button2;
    @BindView(R.id.tv_seek)
    TextView tv_seek;

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_demo3;
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void initView(View rootView) {

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startAnima();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                stopAnima();
            }
        });

        LottieComposition.fromAssetFileName(MyApplication.getContext(), "data2.json", new LottieComposition.OnCompositionLoadedListener() {

            @Override
            public void onCompositionLoaded(LottieComposition composition) {
                animation_view_click.setComposition(composition);
                animation_view_click.setProgress(0.333f);

                animation_view_click.playAnimation();
            }
        });


        animation_view_click.addAnimatorUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                tv_seek.setText(" 动画进度" + (int) (animation.getAnimatedFraction() * 100) + "%");
            }
        });
    }

    @Override
    public void initTopBar() {


    }



    @Override
    public void initData(Bundle arguments) {

    }

    @Override
    public void onLazyLoad() {

    }

    /*
     * 开始动画
     */
    private void startAnima() {

        boolean inPlaying = animation_view_click.isAnimating();
        if (!inPlaying) {
            animation_view_click.setProgress(0f);
            animation_view_click.playAnimation();
        }
    }

    /*
     * 停止动画
     */
    private void stopAnima() {
        boolean inPlaying = animation_view_click.isAnimating();
        if (inPlaying) {
            animation_view_click.cancelAnimation();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(null!=animation_view_click){
            animation_view_click.cancelAnimation();
        }
    }
}
