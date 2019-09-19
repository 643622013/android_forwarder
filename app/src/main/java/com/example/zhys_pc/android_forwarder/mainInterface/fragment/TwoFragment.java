package com.example.zhys_pc.android_forwarder.mainInterface.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.cfbx.framework.rxbus.RxBus;
import com.cfbx.framework.util.DeviceUtils;
import com.cfbx.framework.util.FileUtils;
import com.cfbx.framework.util.LogUtils;
import com.example.zhys_pc.android_forwarder.MyApplication;
import com.example.zhys_pc.android_forwarder.R;
import com.example.zhys_pc.android_forwarder.base.MyBaseActivity;
import com.example.zhys_pc.android_forwarder.base.MyBaseFragment;
import com.example.zhys_pc.android_forwarder.config.C;
import com.example.zhys_pc.android_forwarder.constant.RxBusCode;
import com.example.zhys_pc.android_forwarder.customview.ToolBarBuilder;
import com.example.zhys_pc.android_forwarder.http.entity.MsgEventEntity;
import com.example.zhys_pc.android_forwarder.tools.ImageLoaderUtil;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.tools.PictureFileUtils;

import java.io.File;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Permission;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;


import static android.app.Activity.RESULT_OK;


public class TwoFragment extends MyBaseFragment {
    private ToolBarBuilder toolBarBuilder;


    @BindView(R.id.image1)
    ImageView image1;
    @BindView(R.id.image2)
    ImageView image2;
    @BindView(R.id.image3)
    ImageView image3;
    @BindView(R.id.select_picture)
    ImageView select_picture;


    public static final String girl = "https://raw.githubusercontent.com/sfsheng0322/GlideImageView/master/resources/girl.jpg";
    private List<File> originPhotos = new ArrayList<>();


    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_demo2;
    }

    @Override
    public void initView(View rootView) {
        ImageLoaderUtil.getInstance().loadImage(MyApplication.getContext(), girl, image1);
        ImageLoaderUtil.getInstance().loadCircleImage(MyApplication.getContext(), girl, image2);
        ImageLoaderUtil.getInstance().loadRoundImage(MyApplication.getContext(), girl, image3, DeviceUtils.dip2px(MyApplication.getContext(), 10));
    }

    @OnClick({R.id.select_picture})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.select_picture:
                //选择图片
                requestPermissions(new MyBaseActivity.OnPermissionGranted() {
                    @Override
                    public void onAllGranted() {
                        // 进入相册 以下是例子：用不到的api可以不写
                        PictureSelector.create(TwoFragment.this)
                                .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                                .theme(R.style.picture_default_style)//主题样式(不设置为默认样式) 也可参考demo values/styles下 例如：R.style.picture.white.style
                                .maxSelectNum(10)// 最大图片选择数量 int
                                .minSelectNum(1)// 最小选择数量 int
                                .imageSpanCount(4)// 每行显示个数 int
                                .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                                .previewImage(true)// 是否可预览图片 true or false
                                .previewVideo(true)// 是否可预览视频 true or false
                                .enablePreviewAudio(true) // 是否可播放音频 true or false
                                .isCamera(true)// 是否显示拍照按钮 true or false
                                .imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
                                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                                .sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                                .setOutputCameraPath("/CustomPath")// 自定义拍照保存路径,可不填
                                .enableCrop(false)// 是否裁剪 true or false
                                .compress(true)// 是否压缩 true or false
                                .glideOverride(100, 100)// int glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                                .withAspectRatio(1, 1)// int 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                                .hideBottomControls(false)// 是否显示uCrop工具栏，默认不显示 true or false
                                .isGif(false)// 是否显示gif图片 true or false
                                .compressSavePath(getPath())//压缩图片保存地址
                                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽 true or false
                                .circleDimmedLayer(true)// 是否圆形裁剪 true or false
                                .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false   true or false
                                .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false    true or false
                                .openClickSound(true)// 是否开启点击声音 true or false
//                              .selectionMedia()// 是否传入已选图片 List<LocalMedia> list
                                .previewEggs(true)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中) true or false
                                .cropCompressQuality(90)// 裁剪压缩质量 默认90 int
                                .minimumCompressSize(100)// 小于100kb的图片不压缩
                                .synOrAsy(true)//同步true或异步false 压缩 默认同步
                                .cropWH(1, 1)// 裁剪宽高比，设置如果大于图片本身宽高则无效 int
                                .rotateEnabled(true) // 裁剪是否可旋转图片 true or false
                                .scaleEnabled(true)// 裁剪是否可放大缩小图片 true or false
                                .videoQuality(0)// 视频录制质量 0 or 1 int
                                .videoMaxSecond(15)// 显示多少秒以内的视频or音频也可适用 int
                                .videoMinSecond(10)// 显示多少秒以内的视频or音频也可适用 int
                                .recordVideoSecond(60)//视频秒数录制 默认60s int
                                .isDragFrame(false)// 是否可拖动裁剪框(固定)
                                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
                    }
                }, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE);

                break;
        }
    }




    private String getPath() {
        String path = C.compressedPicturesUrl;
        File file = new File(path);
        if (file.mkdirs()) {
            return path;
        }
        return path;
    }

    @Override
    public void initTopBar() {
        new ToolBarBuilder(mRootView)
                .setLeftImageView(R.drawable.ic_launcher_background)
                .setBackgroundColor(getResources().getColor(R.color.colorAccent), 100)
                .setTitle("测21");

    }

    @Override
    public void initData(Bundle arguments) {

    }

    @Override
    public void onLazyLoad() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片、视频、音频选择结果回调
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true  注意：音视频除外
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true  注意：音视频除外
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                    for (int i = 0; i < selectList.size(); i++) {
                        LogUtils.e("aas", selectList.get(i).getCompressPath() + "");
                        originPhotos.add(new File(selectList.get(i).getCompressPath()));
                    }
                    ImageLoaderUtil.getInstance().loadImage(MyApplication.getContext(), selectList.get(0).getCompressPath(), select_picture);

                    break;
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //包括裁剪和压缩后的缓存，要在上传成功后调用，注意：需要系统sd卡权限
        PictureFileUtils.deleteCacheDirFile(MyApplication.getContext());
    }
}
