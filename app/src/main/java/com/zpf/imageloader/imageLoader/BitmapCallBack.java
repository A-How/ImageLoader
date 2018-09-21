package com.zpf.imageloader.imageLoader;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

public interface BitmapCallBack {

    void onBitmapLoaded(Bitmap bitmap);

    void onBitmapFailed(Drawable drawable);

    public static class EmptyCallBack implements BitmapCallBack
    {

        @Override
        public void onBitmapLoaded(Bitmap bitmap) {

        }

        @Override
        public void onBitmapFailed(Drawable drawable) {

        }

    }

}
