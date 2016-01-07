package com.example.redrock.agriculture.Fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.redrock.agriculture.Const.CommonConst;
import com.example.redrock.agriculture.Model.GoodsDetailModel;
import com.example.redrock.agriculture.Presenter.GoodsDetailPresenter;
import com.example.redrock.agriculture.R;
import com.example.redrock.agriculture.widget.Adapter.RefreshAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class GoodsDetailFragment extends Fragment implements GoodsDetailPresenter.IView, SwipeRefreshLayout.OnRefreshListener {

    private GoodsDetailPresenter presenter;
    private RefreshAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView listView;
    private View loadingView;
    private View loadErrorView;

    public GoodsDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_common_list, container, false);
        presenter = new GoodsDetailPresenter(this, savedInstanceState.getString(CommonConst.URL_BUNDLE_TAG));
        loadingView = rootView.findViewById(R.id.common_list_loading_progress);
        loadErrorView = rootView.findViewById(R.id.common_list_load_error);
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_layout_main_content);
        swipeRefreshLayout.setProgressViewEndTarget(true, 100);
        swipeRefreshLayout.setOnRefreshListener(this);
        listView = (ListView) rootView.findViewById(R.id.list_main_content);
        adapter = new RefreshAdapter(inflater);
        listView.setAdapter(adapter);
        onRefresh();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void notifyRefreshComplete(GoodsDetailModel model) {
        loadingView.setVisibility(View.GONE);
        swipeRefreshLayout.setRefreshing(false);
        swipeRefreshLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onRefresh() {
        if (swipeRefreshLayout.getVisibility() == View.GONE) {
            loadingView.setVisibility(View.VISIBLE);
            loadErrorView.setVisibility(View.GONE);
        }
        presenter.refresh();
    }

    @Override
    public void notifyNetWorkError(VolleyError error) {
        if (swipeRefreshLayout.getVisibility() == View.GONE && loadingView.getVisibility() == View.VISIBLE) {
            loadingView.setVisibility(View.GONE);
            loadErrorView.setVisibility(View.VISIBLE);
            return;
        }
        Toast.makeText(getActivity(), "更新失败", Toast.LENGTH_LONG).show();
    }
}
