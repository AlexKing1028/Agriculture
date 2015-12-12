package com.example.redrock.agriculture.NetTools;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by REDROCK on 12/9/2015.
 */
public class SingletonRequestQueue {
    private static SingletonRequestQueue mInstance;
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;
    private static Context mCtx;
    private SingletonRequestQueue(Context context){
        mCtx=context;
        requestQueue=getRequestQueue();
        imageLoader=new ImageLoader(requestQueue, new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap> cache=new LruCache<>(20);
            @Override
            public Bitmap getBitmap(String url) {
                return cache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                cache.put(url, bitmap);
            }
        }
        );
    }

    public static synchronized SingletonRequestQueue getInstance(Context context){
        if (mInstance == null){
            mInstance= new SingletonRequestQueue(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue(){
        if (requestQueue == null){
            requestQueue= Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req){
        getRequestQueue().add(req);
    }

    public ImageLoader getImageLoader(){
        return imageLoader;
    }
}
