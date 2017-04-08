package com.mvp.travelhope.network;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.mvp.travelhope.utils.AppLogger;

import java.io.UnsupportedEncodingException;
import java.util.Map;


/**
 * Volley adapter for JSON requests that will be parsed into Java objects by
 * Gson.
 *
 * @param <T> the type parameter
 */
public class NetworkRequest<T> extends Request<T> {

    /** Charset for request. */
    private static final String PROTOCOL_CHARSET = "utf-8";

    private static final String TAG = "NetworkRequest";
    private final Class<T> mClass;
    private final NetworkVolleyListener<T> mListener;
    private final Gson mGson = new Gson();
    private final Map<String, String> mParams;
    private final Map<String, String> mHeaders;
    private final String mRequestBody;
    private Context mContext;


    /**
     * Make a POST request and return a parsed object from JSON.
     *
     * @param context     the context
     * @param method      the method
     * @param url         the url
     * @param tClass      the t class
     * @param listener    the listener
     * @param params      the params
     * @param headers     the headers
     * @param requestBody the request body
     */
    public NetworkRequest(Context context, int method, String url, Class<T> tClass, NetworkVolleyListener listener,
                          Map<String, String> params, Map<String, String> headers, String requestBody) {
        super(method, url, listener);
        mContext = context;
        mClass = tClass;
        mListener = listener;
        mParams = params;
        mHeaders = headers;

        mRequestBody = requestBody;
    }



    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return mParams;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return mHeaders;
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        try {
            return mRequestBody == null ? super.getBody() : mRequestBody.getBytes(PROTOCOL_CHARSET);
        } catch(AuthFailureError error) {
            VolleyLog.wtf(error.getMessage());
            return null;
        } catch(UnsupportedEncodingException uee) {
            VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s",
                    mRequestBody, PROTOCOL_CHARSET);
            return null;
        }
    }


    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
                if(json.startsWith("["))
                    json = "{value:"+json+"}";
            AppLogger.i(NetworkRequest.class.getSimpleName(), mClass.getSimpleName()+" response: "+json);
//          writeResponseToFile(json);

            return Response.success(mGson.fromJson(json, mClass), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException ex) {
            Log.d(TAG, ex.getMessage());
            return Response.error(new ParseError(ex));
        } catch (JsonSyntaxException ex) {
            Log.d(TAG, ex.getMessage());
            return Response.error(new ParseError(ex));
        }
    }

    private void writeResponseToFile(String veryLongString) {
        int maxLogSize = 1000;
        for(int i = 0; i <= veryLongString.length() / maxLogSize; i++) {
            int start = i * maxLogSize;
            int end = (i+1) * maxLogSize;
            end = end > veryLongString.length() ? veryLongString.length() : end;
            Log.i(TAG, veryLongString.substring(start, end));
        }

    }
}
