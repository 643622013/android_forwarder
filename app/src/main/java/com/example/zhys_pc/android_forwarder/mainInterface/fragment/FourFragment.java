package com.example.zhys_pc.android_forwarder.mainInterface.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.zhys_pc.android_forwarder.MyApplication;
import com.example.zhys_pc.android_forwarder.R;
import com.example.zhys_pc.android_forwarder.base.MyBaseFragment;
import com.example.zhys_pc.android_forwarder.mainInterface.adapter.DemoAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class FourFragment extends MyBaseFragment {
    //    @BindView(R.id.image31)
//    ImageView image31;
//    @BindView(R.id.image32)
//    GlideImageView image32;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private DemoAdapter demoAdapter;
    private List<String> dataList = new ArrayList<>();

    public final String girl = "https://raw.githubusercontent.com/sfsheng0322/GlideImageView/master/resources/girl.jpg";

    @SuppressLint("WrongConstant")
    @Override
    public void initView(View rootView) {
//        ImageLoaderUtils.load(MyApplication.getContext(), image31, girl, R.drawable.ic_launcher_background, R.drawable.ic_launcher_background, 5);
//        image32.centerCrop().error(R.mipmap.image_load_err).diskCacheStrategy(DiskCacheStrategy.NONE).load(girl, R.color.placeholder, 5, (isComplete, percentage, bytesRead, totalBytes) -> {
////            Log.d("--->", "load percentage: " + percentage + " totalBytes: " + totalBytes + " bytesRead: " + bytesRead);
//
//        });

        for (int i = 0; i < 100; i++) {
            dataList.add("");
        }
        demoAdapter = new DemoAdapter(dataList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MyApplication.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setAdapter(demoAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void initTopBar() {

    }

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_demo4;
    }

    @Override
    public void initData(Bundle arguments) {

    }

    @Override
    public void onLazyLoad() {

    }
}
