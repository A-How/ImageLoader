package com.zpf.imageloader.imageLoader;

import android.graphics.Bitmap;

public interface BitmapCallBack {

    void onBitmapLoaded(Bitmap bitmap);

    void onBitmapFailed(Exception e);

    public static class EmptyCallBack implements BitmapCallBack{

        @Override
        public void onBitmapLoaded(Bitmap bitmap) {

        }

        @Override
        public void onBitmapFailed(Exception e) {

        }
    }

}
