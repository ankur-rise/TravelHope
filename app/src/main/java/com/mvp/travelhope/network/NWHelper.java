package com.mvp.travelhope.network;

import android.content.Context;

import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.mvp.travelhope.R;

import java.util.Map;

/**
 * Helper Class which makes volley requests
 *
 * @param <T> the type parameter
 */
public class NWHelper<T> implements NetworkInterface {
    private static NWHelper minstanceNWhelper = null;
    private static final String TAG = NWHelper.class.getSimpleName();

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static NWHelper getInstance() {
        if (minstanceNWhelper == null) {
            minstanceNWhelper = new NWHelper();
            return minstanceNWhelper;
        } else return minstanceNWhelper;

    }

    @Override
    public <T> void commonRequest(final Context context, final NetworkListener listener, int methodType, String url,
                                  int reqType, Class<T> clazz, Map<String, String> params, Map<String, String>
                                              headers, String body, final String TAG) {

        NetworkRequest networkRequest = new NetworkRequest(context, methodType, url, clazz, new NetworkVolleyListener
                (null, new NetworkVolleyListener.onUpdateListener<T>() {
            @Override
            public void onSuccess(T response, int reqType) {
                listener.onSuccess(response, reqType);
            }

            @Override
            public void onError(VolleyError error, int reqType) {

                String errorMsg;
                if (error instanceof NoConnectionError) {
                    errorMsg = context.getString(R.string.err_01_no_network);
                } else if (error instanceof ServerError) {
                    errorMsg = context.getString(R.string.err_01_no_network);
                } else if (error instanceof TimeoutError) {
                    errorMsg = context.getString(R.string.err_01_no_network);
                } else {
                    errorMsg = context.getString(R.string.err_01_no_network);
                }

                listener.onError(errorMsg, reqType);

            }
        }, reqType), params, headers, body);
        VolleyController.getInstance(context).addToRequestQueue(networkRequest, TAG);
    }



    /**
     * to make POST request
     *
     * @param listener
     * @param url
     * @param reqType
     * @param clazz
     * @param params
     * @param headers
     * @param TAG
     */
    @Override
    public <T> void post(Context context, final NetworkListener listener, String url, int reqType, Class<T> clazz,
                         Map<String, String> params, Map<String, String> headers, final String TAG) {
        commonRequest(context, listener, Request.Method.POST, url, reqType, clazz, params, headers, null, TAG);
    }


    @Override
    public <T> void get(Context context, NetworkListener listener, String url, Class aClass, Map<String, String>
            headers, int reqType, final String tag) {
        commonRequest(context, listener, Request.Method.GET, url, reqType, aClass, null, headers, null, tag);
    }

}
