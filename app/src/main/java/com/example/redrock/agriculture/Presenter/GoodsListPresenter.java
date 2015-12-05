package com.example.redrock.agriculture.Presenter;

import com.example.redrock.agriculture.Tools.Item;

import java.util.ArrayList;

/**
 * Created by REDROCK on 11/29/2015.
 */
public interface GoodsListPresenter {
    void refreshData();

    interface GoodsListView {
        void notifyDataChangeReady(ArrayList<Item> data, int MODE);
    }
}
