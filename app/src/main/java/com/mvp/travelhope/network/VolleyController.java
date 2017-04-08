package com.mvp.travelhope.network;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.text.TextUtils;

import com.android.volley.Cache;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.ImageLoader;

public class VolleyController {
    private static final String TAG = "VolleyController";
    private static final int REQ_TIMEOUT_MS_2G = 10000;
    private static final int MAX_RETRIES_2G = 2;

    private static VolleyController sObj;
    private RequestQueue mRequestQueue;
    private Context mContext;
    private ImageLoader mImageLoader;

    private VolleyController(Context context)
    {
        mContext = context;
        mRequestQueue = getRequestQueue();
        mImageLoader = new ImageLoader(mRequestQueue,
                new ImageLoader.ImageCache() {
                    private final LruCache<String, Bitmap>
                            cache = new LruCache<String, Bitmap>(20);

                    @Override
                    public Bitmap getBitmap(String url) {
                        return cache.get(url);
                    }

                    @Override
                    public void putBitmap(String url, Bitmap bitmap) {
                        cache.put(url, bitmap);
                    }
                });
    }

    public static synchronized VolleyController getInstance(Context context) {
        if (sObj == null) sObj = new VolleyController(context);
        return sObj;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {

            Cache cache = new DiskBasedCache(mContext.getCacheDir(), 10 * 1024 * 1024);
            Network network = new BasicNetwork(new HurlStack());
            mRequestQueue = new RequestQueue(cache, network);
            mRequestQueue.start();

        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        //  if (is2gNetwork()) {
        req.setRetryPolicy(new DefaultRetryPolicy(0, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        // }
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }



    public ImageLoader getImageLoader() {
        return mImageLoader;
    }

}
