package com.zpf.imageloader.imageLoader;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.support.v4.content.Loader;
import android.view.View;

import java.io.File;

public class LoaderOptions {

    public int placeholderResId;
    public int errorResId;
    public boolean isCenterCrop;
    public boolean isCenterInside;
    public boolean skipLocalCache;//是否缓存在本地
    public boolean skipNetCache;
    public Bitmap.Config config = Bitmap.Config.RGB_565;
    public int targetWidth;
    public int targetHeight;
    public float bitmapAngle;//圆角角度
    public float degrees;//旋转角度.注意:picasso针对三星等本地图片，默认旋转回0度，即正常位置。此时不需要自己rotate
    public Drawable placeHolder;
    public View targetView;
    public BitmapCallBack callBack;
    public String url;
    public File file;
    public int drawableResId;
    public Uri uri;

    public LoaderOptions(String url){
        this.url = url;
    }

    public LoaderOptions(File file){
        this.file = file;
    }

    public LoaderOptions(int drawableResId){
        this.drawableResId = drawableResId;
    }

    public LoaderOptions(Uri uri){
        this.uri = uri;
    }

    public void into(View targetView){
        this.targetView = targetView;
        ImageLoader.getInstance().loadOptions(this);
    }

    public void bitmap(BitmapCallBack bitmapCallBack){
        this.callBack = bitmapCallBack;
        ImageLoader.getInstance().loadOptions(this);
    }

    public LoaderOptions placeholder(@DrawableRes int placeholderResId){
        this.placeholderResId = placeholderResId;
        return this;
    }

    public LoaderOptions placeholder(Drawable placeHolder){
        this.placeHolder = placeHolder;
        return this;
    }

    public LoaderOptions error(@DrawableRes int errorResId){
        this.errorResId = errorResId;
        return this;
    }

    public LoaderOptions centerCrop(){
        isCenterCrop = true;
        return this;
    }

    public LoaderOptions centerInside(){
        isCenterInside = true;
        return this;
    }

    public LoaderOptions config(Bitmap.Config config){
        this.config = config;
        return this;
    }

    public LoaderOptions resize (int targetWidth,int targetHeight){
        this.targetWidth = targetWidth;
        this.targetHeight = targetHeight;
        return this;
    }

    public LoaderOptions angle(float bitmapAngle){
        this.bitmapAngle = bitmapAngle;
        return this;
    }

    public LoaderOptions skipLocalCache(boolean skipLocalCache){
        this.skipLocalCache = skipLocalCache;
        return this;
    }

    public LoaderOptions skipNetCache(boolean skipNetCache){
        this.skipNetCache = skipNetCache;
        return this;
    }

    public LoaderOptions rotate(float degress){
        this.degrees = degress;
        return this;
    }



}
