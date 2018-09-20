package com.zpf.imageloader.imageLoader;


import android.net.Uri;

import java.io.File;

/**
 * 图片管理类，对外提供接口
 * 静态代理模式，开发者只需关注 ImageLoader + LoaderOptions
 */
public class ImageLoader {

    private static ILoaderStrategy sLoader;
    private static volatile ImageLoader sInstance;

    private ImageLoader() {
    }

    public static ImageLoader getInstance() {
        if (sInstance == null) {
            synchronized (ImageLoader.class) {
                if (sInstance == null) {
                    sInstance = new ImageLoader();
                }
            }
        }
        return sInstance;
    }

    public void setImageLoader(ILoaderStrategy loader) {
        if (null != loader) {
            sLoader = loader;
        }
    }

    public LoaderOptions load(String path) {
        return new LoaderOptions(path);
    }

    public LoaderOptions load(File file) {
        return new LoaderOptions(file);
    }

    public LoaderOptions load(int drawable) {
        return new LoaderOptions(drawable);
    }

    public LoaderOptions load(Uri uri) {
        return new LoaderOptions(uri);
    }

    public void loadOptions(LoaderOptions options) {
        sLoader.loadImage(options);
    }

    public void clearMemoryCache() {
        sLoader.clearMemoryCache();
    }

    public void clearDiskCache() {
        sLoader.clearDiskCache();
    }


}