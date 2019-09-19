package com.example.zhys_pc.android_forwarder.tools;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.example.zhys_pc.android_forwarder.R;


public class ImageLoaderUtil {
    static ImageLoaderUtil instance;

    public ImageLoaderUtil() {
    }

    public static ImageLoaderUtil getInstance() {
        if (null == instance) {
            synchronized (ImageLoaderUtil.class) {
                if (null == instance) {
                    instance = new ImageLoaderUtil();
                }
            }
        }
        return instance;
    }

    /**
     * 普通加载 * @param context * @param url * @param imageView
     */
    public void loadImage(Context context, String url, ImageView imageView) {
        RequestOptions options = new RequestOptions().centerCrop().placeholder(R.drawable.ic_error_icon_nowifi).error(R.drawable.ic_error_icon_nowifi).dontAnimate();
        Glide.with(context).load(url).apply(options).into(imageView);
    }

    /**
     * 加载圆形图片 * @param context * @param url * @param imageView
     */
    public void loadCircleImage(Context context, String url, ImageView imageView) {
        RequestOptions options = new RequestOptions().circleCrop().placeholder(R.drawable.ic_error_icon_nowifi).error(R.drawable.ic_error_icon_nowifi);
        Glide.with(context).load(url).apply(options).into(imageView);
    }


    /**
     * 加载圆形图片 * @param context * @param url * @param imageView
     */
    public void loadCircleImage(Context context, Uri url, ImageView imageView) {
        RequestOptions options = new RequestOptions().circleCrop().placeholder(R.drawable.ic_error_icon_nowifi).error(R.drawable.ic_error_icon_nowifi);
        Glide.with(context).load(url).apply(options).into(imageView);
    }


    /**
     * 加载圆角图片 * * @param context * @param url * @param imageView
     */
    public void loadRoundImage(Context context, String url, ImageView imageView, int radius) {
        RequestOptions options = RequestOptions.bitmapTransform(new RoundedCorners(radius)).placeholder(R.drawable.ic_error_icon_nowifi).error(R.drawable.ic_error_icon_nowifi);
        Glide.with(context).load(url).apply(options).into(imageView);
    }

    /**
     * 加载圆角图片 * * @param context * @param url * @param imageView
     */
    public void loadRoundImage(Context context, int url, ImageView imageView, int radius) {

        RequestOptions myOptions = new RequestOptions()
                .centerCrop()
                .transform(new GlideRoundTransform(context, radius))
                .placeholder(R.drawable.ic_error_icon_nowifi).error(R.drawable.ic_error_icon_nowifi);

        Glide.with(context)
                .load(url)
                .apply(myOptions)
                .into(imageView);


    }

    /**
     * 加载圆角图片 * * @param context * @param url * @param imageView
     */
    public void loadRoundImage(Context context, Bitmap bitmap, ImageView imageView, int radius) {
        RequestOptions options = RequestOptions.bitmapTransform(new RoundedCorners(radius)).placeholder(R.drawable.ic_error_icon_nowifi).error(R.drawable.ic_error_icon_nowifi);
        Glide.with(context).load(bitmap).apply(options).into(imageView);
    }
}
