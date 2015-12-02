package com.example.redrock.agriculture.Presenter;

import com.example.redrock.agriculture.Model.InfoListDataModel;

import java.util.ArrayList;

/**
 * Created by REDROCK on 11/29/2015.
 */
public class InfoListPresenterImp implements InfoListPresenter, InfoListDataModel.OnFetchListener {
    InfoListView infoListView;
    InfoListDataModel infoListDataModel;

    public InfoListPresenterImp(InfoListView infoListView) {
        this.infoListView = infoListView;
        infoListDataModel = new InfoListDataModel();
    }
    @Override
    public void refreshData() {
        infoListDataModel.fetchInfoData(this);
    }

    @Override
    public void onFetch(ArrayList<String> data) {
        infoListView.notifyDataChangeReady(data, 0);
    }
}
