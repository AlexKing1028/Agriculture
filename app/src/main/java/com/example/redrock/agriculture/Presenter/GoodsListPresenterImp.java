package com.example.redrock.agriculture.Presenter;

import com.example.redrock.agriculture.Model.InfoListDataModel;
import com.example.redrock.agriculture.widget.ListItem.Item;

import java.util.ArrayList;

/**
 * Created by REDROCK on 11/29/2015.
 */
public class GoodsListPresenterImp implements GoodsListPresenter,InfoListDataModel.OnFetchListener{
    GoodsListView goodsListView;
    InfoListDataModel infoListDataModel;

    public GoodsListPresenterImp(GoodsListPresenter.GoodsListView goodsListView) {
        this.goodsListView = goodsListView;
        infoListDataModel = new InfoListDataModel();
    }
    @Override
    public void refreshData() {
        infoListDataModel.fetchDataFromInternet(this);
    }

    @Override
    public void onFetch(ArrayList<Item> data) {
        goodsListView.notifyDataChangeReady(data, 0);
    }

}
