package com.example.redrock.agriculture.Presenter;

import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.example.redrock.agriculture.Const.CommonConst;
import com.example.redrock.agriculture.NetTools.Request.PostRequest;
import com.example.redrock.agriculture.NetTools.SingletonRequestQueue;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by REDROCK on 1/3/2016.
 */
public class LoginPresenter {
    private LoginView view;

    public void attachView(LoginView view) {
        this.view = view;
    }

    public void detachView() {
        this.view = null;
    }

    public void login(CharSequence name, CharSequence password) {
        Map<String, String> params = new HashMap<>();
        params.put("password", password.toString());
        params.put("name", name.toString());
        PostRequest login_req = new PostRequest<Object>(CommonConst.HOST + "/login", new Response.Listener<Object>() {
            @Override
            public void onResponse(Object response) {
                view.loginSuccess();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                view.loginError(error.getMessage());
                if (error == null || error.networkResponse == null) {
                    return;
                }
                int code = error.networkResponse.statusCode;
                Log.d(CommonConst.LOGTAG, code + " is the status code");
            }
        }, params) {
            @Override
            protected Response parseNetworkResponse(NetworkResponse response) {
                try {
                    String data= new String(response.data, HttpHeaderParser.parseCharset(response.headers));
                    return Response.success(data, HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                }
            }
        };
        SingletonRequestQueue.getInstance().addToRequestQueue(login_req);
    }

    public interface LoginView {
        void loginSuccess();

        void loginError(String reason);
    }
}