package com.example.redrock.agriculture.NetTools.Request;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.example.redrock.agriculture.NetTools.Deserialize.Deserializer;

/**
 * Created by REDROCK on 12/10/2015.
 */
public class GsonDeserializeRequest<T> extends Request<T> {
    private final Deserializer<T> deserializer;
    private final Response.Listener<T> listener;

    public GsonDeserializeRequest(String url, Deserializer<T> deserializer,
                                  Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(Method.GET, url, errorListener);
        this.deserializer = deserializer;
        this.listener = listener;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try{
            String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            return Response.success(deserializer.deserialize(json), HttpHeaderParser.parseCacheHeaders(response));
        }catch (Exception e){
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(T response) {
        listener.onResponse(response);
    }
}
