package com.zpf.imageloader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.zpf.imageloader.imageLoader.ImageLoader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = findViewById(R.id.main_img);

        ImageLoader.getInstance().setImageLoader(new PicassoLoader());
        ImageLoader.getInstance().load("").into(imageView);

    }
}
