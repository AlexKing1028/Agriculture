package com.example.redrock.agriculture.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.redrock.agriculture.R;
import com.example.redrock.agriculture.widget.LoadMoreListView;
import com.example.redrock.agriculture.widget.CustomSwipeToRefreshView;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class RefreshListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    protected LoadMoreListView.LoadMoreAdapter adapter;
    protected CustomSwipeToRefreshView customSwipeToRefreshView;
    protected LoadMoreListView listView;
    protected LayoutInflater inflater;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.inflater=inflater;
        View rootView = inflater.inflate(R.layout.fragment_refresh_list, container, false);
        customSwipeToRefreshView = (CustomSwipeToRefreshView) rootView.findViewById(R.id.swipe_layout_main_content);
        customSwipeToRefreshView.setProgressViewEndTarget(true, 100);
        customSwipeToRefreshView.setOnRefreshListener(this);
        listView = (LoadMoreListView) rootView.findViewById(R.id.list_main_content);
        /**
         * todo .. something amazing
         */
        adapter = createAdapter();
        listView.setAdapter(adapter);
        listView.setListener(adapter);
        onRefresh();
        return rootView;
    }

    protected abstract LoadMoreListView.LoadMoreAdapter createAdapter();

    @Override
    public void onRefresh() {
        customSwipeToRefreshView.setRefreshing(true);
    }
}
