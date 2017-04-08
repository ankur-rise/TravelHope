package com.mvp.travelhope.network;

import android.content.Context;

import java.util.Map;

/**
 * Interface for calls to network
 */

public interface NetworkInterface {
    <T> void commonRequest(final Context context, final NetworkListener listener, int methodType, String url,
                                  int reqType, Class<T> clazz, Map<String, String> params, Map<String, String>
                                          headers, final String TAG, String body);

    <T> void post(Context context, final NetworkListener listener, String url, int reqType, Class<T> clazz,
                         Map<String, String> params, Map<String, String> headers, final String TAG);



    <T> void get(Context context, final NetworkListener listener, String url, final Class aClass, Map<String,
            String> header, int reqType, final String Tag);


}
