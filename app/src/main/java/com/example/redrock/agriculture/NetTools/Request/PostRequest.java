package com.example.redrock.agriculture.NetTools.Request;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by REDROCK on 1/3/2016.
 */
public abstract class PostRequest<T> extends Request<T>{
    private Map<String, String> mMap;
    private Response.Listener<T> mListener;

    public PostRequest(String url, Response.Listener<T> listener, Response.ErrorListener error, Map<String, String> params){
        super(Method.POST, url, error);
        mListener = listener;
        mMap = params;
    }

    @Override
    protected Map<String, String> getParams() {
        return mMap;
    }

    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }
}
