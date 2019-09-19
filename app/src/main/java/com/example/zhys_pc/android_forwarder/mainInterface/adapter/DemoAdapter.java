package com.example.zhys_pc.android_forwarder.mainInterface.adapter;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.cfbx.framework.util.DeviceUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.zhys_pc.android_forwarder.MyApplication;
import com.example.zhys_pc.android_forwarder.R;


import java.util.List;

import androidx.annotation.Nullable;

/**
 * @author lilei
 * create at 2019/5/27 17:33
 * @desc 测试demo
 */

public class DemoAdapter extends BaseQuickAdapter<String, DemoAdapter.ViewHolder> {
    public final String girl = "https://raw.githubusercontent.com/sfsheng0322/GlideImageView/master/resources/girl.jpg";


    public DemoAdapter(@Nullable List<String> data) {
        super(R.layout.item_demo, data);
    }

    @Override
    protected void convert(ViewHolder holder, String item) {

    }


    public class ViewHolder extends BaseViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

}
