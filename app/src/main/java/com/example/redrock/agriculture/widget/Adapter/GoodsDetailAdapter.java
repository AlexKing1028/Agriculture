package com.example.redrock.agriculture.widget.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.redrock.agriculture.widget.LoadMoreListView;

/**
 * Created by REDROCK on 1/6/2016.
 */
public class GoodsDetailAdapter extends LoadMoreListView.LoadMoreAdapter {
    public GoodsDetailAdapter(LayoutInflater inflater, LoadMoreListView view) {
        super(inflater, view);
    }

    @Override
    public int getPageSize() {
        return 0;
    }

    @Override
    public void loadMoreData(int startCursor, int size) {

    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
