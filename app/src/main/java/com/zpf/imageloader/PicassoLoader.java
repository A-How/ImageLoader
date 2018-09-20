package com.zpf.imageloader;


import com.squareup.picasso.LruCache;
import com.zpf.imageloader.imageLoader.ILoaderStrategy;
import com.zpf.imageloader.imageLoader.LoaderOptions;

public class PicassoLoader implements ILoaderStrategy {


    private static LruCache sLruCache = new LruCache(App.gapp);


    @Override
    public void loadImage(LoaderOptions options) {

    }

    @Override
    public void clearMemoryCache() {

    }

    @Override
    public void clearDiskCache() {

    }


}
