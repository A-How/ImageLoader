package com.zpf.imageloader;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.squareup.picasso.LruCache;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import com.squareup.picasso.Target;
import com.squareup.picasso.Transformation;
import com.zpf.imageloader.imageLoader.BitmapCallBack;
import com.zpf.imageloader.imageLoader.ILoaderStrategy;
import com.zpf.imageloader.imageLoader.LoaderOptions;

import java.io.File;

public class PicassoLoader implements ILoaderStrategy {

    private volatile static Picasso sPicassoSingleton;
    private final String PICASSO_CACHE = "picasso-cache";
    private static LruCache sLruCache = new LruCache(App.gapp);

    private static Picasso getPicasso(){
        if(sPicassoSingleton != null){
            synchronized (PicassoLoader.class){
                if (sPicassoSingleton != null){
                    sPicassoSingleton = new Picasso.Builder(App.gapp).memoryCache(sLruCache).build();
                }
            }
        }
        return sPicassoSingleton;
    }


    @Override
    public void clearMemoryCache() {
        sLruCache.clear();
    }

    @Override
    public void clearDiskCache() {
        File diskCache = new File(App.gapp.getCacheDir(),PICASSO_CACHE);
        if (diskCache.exists()){
            //这边自写删除的代码

        }
    }


    @Override
    public void loadImage(LoaderOptions options) {
        RequestCreator requestCreator = null;
        if (options.url != null){
            requestCreator = getPicasso().load(options.url);
        }else if (options.uri != null){
            requestCreator = getPicasso().load(options.uri);
        }else if (options.drawableResId != 0){
            requestCreator = getPicasso().load(options.drawableResId);
        }else if (options.file != null){
            requestCreator = getPicasso().load(options.file);
        }

        if (requestCreator == null){
            throw new NullPointerException("requestCreator must not be null");
        }

        if (options.targetWidth > 0 && options.targetHeight > 0){
            requestCreator.resize(options.targetWidth,options.targetHeight);
        }

        if (options.isCenterInside){
            requestCreator.centerInside();
        }else if (options.isCenterCrop){
            requestCreator.centerCrop();
        }

        if (options.config != null){
            requestCreator.config(options.config);
        }

        if (options.errorResId != 0){
            requestCreator.error(options.errorResId);
        }

        if (options.placeholderResId != 0){
            requestCreator.placeholder(options.placeholderResId);
        }

        if (options.bitmapAngle != 0){
            requestCreator.transform(new PicassoTransformation(options.bitmapAngle));
        }

        if (options.skipLocalCache){
            requestCreator.memoryPolicy(MemoryPolicy.NO_CACHE,MemoryPolicy.NO_STORE);
        }

        if (options.skipNetCache){
            requestCreator.networkPolicy(NetworkPolicy.NO_CACHE,NetworkPolicy.NO_STORE);
        }

        if (options.degrees != 0){
            requestCreator.rotate(options.degrees);
        }

        if (options.targetView instanceof ImageView){
            requestCreator.into((ImageView) options.targetView);
        }else if (options.callBack != null){

        }
    }


    class PicassoTarget implements Target{

        BitmapCallBack callBack;

        protected PicassoTarget(BitmapCallBack callBack){
            this.callBack = callBack;
        }


        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            if (this.callBack != null){
                callBack.onBitmapLoaded(bitmap);
            }
        }

        @Override
        public void onBitmapFailed(Drawable drawable) {
            if (this.callBack != null){
                callBack.onBitmapFailed(drawable);
            }
        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {

        }
    }


    class PicassoTransformation implements Transformation{

        private float bitmapAngle;

        protected PicassoTransformation (float corner){
            this.bitmapAngle = corner;
        }

        @Override
        public Bitmap transform(Bitmap source) { //根据实际项目 进行配置
            float roundX = bitmapAngle; //圆角的横向半径和纵向半径
            Bitmap bitmap = Bitmap.createBitmap(source.getWidth(),source.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            final int color = 0xff424242;
            final Paint paint = new Paint();
            final Rect rect = new Rect(0,0,source.getWidth(),source.getHeight());
            final RectF rectF = new RectF(rect);
            paint.setAntiAlias(true);
            canvas.drawARGB(0,0,0,0);
            paint.setColor(color);
            canvas.drawRoundRect(rectF,roundX,roundX,paint);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            canvas.drawBitmap(source, rect, rect, paint);
            source.recycle();
            return bitmap;
        }

        @Override
        public String key() {
            return "bitmapAngle()";
        }
    }


}
