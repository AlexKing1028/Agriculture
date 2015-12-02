package com.example.redrock.agriculture.Presenter;

import java.util.ArrayList;

/**
 * Created by REDROCK on 11/29/2015.
 */
public interface GoodsListPresenter {
    void refreshData();

    interface GoodsListView {
        void notifyDataChangeReady(ArrayList<String> data, int MODE);
    }
}
