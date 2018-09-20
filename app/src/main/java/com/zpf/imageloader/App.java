package com.zpf.imageloader;

import android.app.Application;

import com.zpf.imageloader.imageLoader.ImageLoader;

public class App extends Application {


    public static App gapp;


    @Override
    public void onCreate() {
        super.onCreate();
        gapp = this;

        ImageLoader.getInstance().setImageLoader(new PicassoLoader());

    }
}
