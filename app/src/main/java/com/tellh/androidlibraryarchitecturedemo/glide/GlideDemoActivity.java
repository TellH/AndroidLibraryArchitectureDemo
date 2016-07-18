package com.tellh.androidlibraryarchitecturedemo.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.tellh.androidlibraryarchitecturedemo.R;

public class GlideDemoActivity extends AppCompatActivity {
    private SimpleTarget<Bitmap> simpleTarget;
    private RequestListener<String, GlideDrawable> requestListener = new RequestListener<String, GlideDrawable>() {
        @Override
        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
            // todo log exception
            Log.d("GlideDemoActivity", "onException() called with: " + "e = [" + e + "], model = [" + model + "], target = [" + target + "], isFirstResource = [" + isFirstResource + "]");
            // important to return false so the error placeholder can be placed
            return false;
        }

        @Override
        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
            Log.d("GlideDemoActivity", "onResourceReady() called with: " + "resource = [" + resource + "], model = [" + model + "], target = [" + target + "], isFromMemoryCache = [" + isFromMemoryCache + "], isFirstResource = [" + isFirstResource + "]");
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_demo);
        loadThumbnailImg();
        loadSimpleTargetImg();
        loadBlurTransformImg();

    }

    private void loadBlurTransformImg() {
        ImageView ivBlurTransform = (ImageView) findViewById(R.id.iv_blurTransform);
        Context context=GlideDemoActivity.this;
        assert ivBlurTransform != null;
        Glide.with(context)
                .load("https://raw.githubusercontent.com/TellH/AndroidLibraryArchitectureDemo/master/raw/xiaomeng.jpg")
                .transform(new BlurTransformation(context))
                //.bitmapTransform( new BlurTransformation( context ) ) // this would work too!
                .into(ivBlurTransform);
    }

    private void loadSimpleTargetImg() {
        final ImageView ivSimpleTarget = (ImageView) findViewById(R.id.iv_simpleTarget);
        assert ivSimpleTarget != null;
        simpleTarget = new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                Log.d("GlideDemoActivity", "onResourceReady() called with: " + "resource = [" + resource + "], glideAnimation = [" + glideAnimation + "]");
                ivSimpleTarget.setImageBitmap(resource);
            }
        };
        Glide.with(GlideDemoActivity.this)
                .load("https://raw.githubusercontent.com/TellH/AndroidLibraryArchitectureDemo/master/raw/xiaomeng.jpg")
                .asBitmap()
                .into(simpleTarget);
    }

    private void loadThumbnailImg() {
        ImageView ivThumbnail = (ImageView) findViewById(R.id.iv_thumbnail);
        assert ivThumbnail != null;
        Glide.with(GlideDemoActivity.this)
                .load("https://raw.githubusercontent.com/TellH/AndroidLibraryArchitectureDemo/master/raw/xiaomeng.jpg")
//                .override(500,800)
                .listener(requestListener)
                .thumbnail(0.5f)
                .into(ivThumbnail);
    }
}
