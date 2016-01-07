package com.example.redrock.agriculture.Presenter;

import com.example.redrock.agriculture.widget.ListItem.Item;

import java.util.ArrayList;

/**
 * Created by REDROCK on 12/30/2015.
 */
public interface GoodsRefreshListPresenter {
    void refreshData();
    interface GoodsRefreshListView {
        void notifyDataChangeReady(ArrayList<Item> data, int MODE);
    }
}
