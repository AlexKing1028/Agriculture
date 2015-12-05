package com.example.redrock.agriculture.Presenter;

import com.example.redrock.agriculture.Tools.Item;

import java.util.ArrayList;

/**
 * Created by REDROCK on 11/29/2015.
 */
public interface InfoListPresenter {
    void refreshData();

    interface InfoListView {
        void notifyDataChangeReady(ArrayList<Item> data, int MODE);
    }
}
