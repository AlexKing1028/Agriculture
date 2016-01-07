package com.example.redrock.agriculture.NetTools;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.HttpClientStack;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;

import khandroid.ext.apache.http.client.CookieStore;

/**
 * Created by REDROCK on 12/9/2015.
 */
public class SingletonRequestQueue {
    private static final String KEY_SOTRE_TYPE = "bks";
    private static SingletonRequestQueue mInstance;
    private static Context mCtx;
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;

    private SingletonRequestQueue(Context context) {
        mCtx = context;
        requestQueue = getRequestQueue();
        imageLoader = new ImageLoader(requestQueue, new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap> cache = new LruCache<>(20);

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

    public static synchronized SingletonRequestQueue getInstance(){
        return getInstance(null);
    }

    public static synchronized SingletonRequestQueue getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SingletonRequestQueue(context);
        }
        return mInstance;
    }

    private static SSLSocketFactory getSslSocketFactory(Context context) {
        try {
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            KeyStore trustStore = KeyStore.getInstance("bks");
            InputStream ksIn = context.getResources().getAssets().open("client.p12");
            InputStream tsIn = context.getResources().getAssets().open("agriculture.bks");
            try {
                keyStore.load(ksIn, "123456".toCharArray());
                trustStore.load(tsIn, "123456".toCharArray());
                Certificate certificate = trustStore.getCertificate("server");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    ksIn.close();
                } catch (Exception e) {
                }
                try {
                    tsIn.close();
                } catch (Exception e) {
                }
            }
            SSLSocketFactory factory = new SSLSocketFactory(keyStore, "123456", trustStore);
            factory.setHostnameVerifier(new AllowAllHostnameVerifier());
            return factory;
        } catch (KeyManagementException | UnrecoverableKeyException | NoSuchAlgorithmException|KeyStoreException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            SSLSocketFactory socketFactory = getSslSocketFactory(mCtx);
            if (socketFactory != null){
                Scheme sch = new Scheme("https", socketFactory, 9000);
                httpClient.getConnectionManager().getSchemeRegistry().register(sch);
            }
            httpClient.setCookieStore(new PreferencesCookieStore(mCtx));
            HttpClientStack clientStack = new HttpClientStack(httpClient);
            requestQueue = Volley.newRequestQueue(mCtx.getApplicationContext(), clientStack);
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    public ImageLoader getImageLoader() {
        return imageLoader;
    }
}
