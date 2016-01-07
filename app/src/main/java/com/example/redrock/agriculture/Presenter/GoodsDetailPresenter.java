package com.example.redrock.agriculture.Presenter;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.redrock.agriculture.Model.GoodsDetailModel;
import com.example.redrock.agriculture.NetTools.Request.GsonRequest;
import com.example.redrock.agriculture.NetTools.SingletonRequestQueue;

/**
 * Created by REDROCK on 1/6/2016.
 */
public class GoodsDetailPresenter {
    IView view;
    String request;

    public GoodsDetailPresenter(IView view, String request) {
        this.view = view;
        this.request = request;
    }

    public void refresh() {
        SingletonRequestQueue.getInstance().addToRequestQueue(new GsonRequest<GoodsDetailModel>(request,
                GoodsDetailModel.class, new Response.Listener<GoodsDetailModel>() {
            @Override
            public void onResponse(GoodsDetailModel response) {
                view.notifyRefreshComplete(response);
        }},new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                view.notifyNetWorkError(error);
            }
        }));
    }

    public interface IView {
        void notifyRefreshComplete(GoodsDetailModel data);

        void notifyNetWorkError(VolleyError error);
    }
}
