package com.example.redrock.agriculture.Fragment;

import android.app.Activity;
import android.app.Fragment;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.redrock.agriculture.Presenter.GoodsRefreshListPresenter;
import com.example.redrock.agriculture.widget.LoadMoreListView;
import com.example.redrock.agriculture.widget.Adapter.GoodsRefreshListAdapter;
import com.example.redrock.agriculture.widget.ListItem.BriefPictureItem;
import com.example.redrock.agriculture.widget.ListItem.Item;
import com.example.redrock.agriculture.widget.ListItem.SimpleItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GoodsRefreshListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GoodsRefreshListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GoodsRefreshListFragment extends RefreshListFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private GoodsRefreshListPresenter goodsRefreshListPresenter;

    public GoodsRefreshListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GoodsRefreshListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GoodsRefreshListFragment newInstance(String param1, String param2) {
        GoodsRefreshListFragment fragment = new GoodsRefreshListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    protected LoadMoreListView.LoadMoreAdapter createAdapter() {
        return new GoodsRefreshListAdapter(inflater, listView);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        if (goodsRefreshListPresenter != null) goodsRefreshListPresenter.refreshData();
        new DataMockAsync().execute(20);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

    class DataMockAsync extends AsyncTask<Integer, Integer, List<Item>> {
        @Override
        protected List<Item> doInBackground(Integer... params) {
            int size = params[0];
            List<Item> result = new ArrayList<>();
            Random random = new Random();
            int i = random.nextInt(1000);
            for (int j = 0; j < size; j++) {
                int tmp = random.nextInt(1000) % 2;
                if (tmp == 1) {
                    String title = "title" + j + i;
                    String info = "info" + j;
                    SimpleItem simpleItem = new SimpleItem(title, info);
                    result.add(simpleItem);
                } else if (tmp == 2) {
                    String title = "title pic" + j + i;
                    String info = "this" + i + " is info" + j;
                    BriefPictureItem briefPictureItem = new BriefPictureItem(title, info, "R.drawable.jiulaba");
                    result.add(briefPictureItem);
                }
            }
            return result;
        }

        @Override
        protected void onPostExecute(List<Item> result) {
            adapter.addAll(result);
            adapter.notifyDataSetChanged();
            customSwipeToRefreshView.setRefreshing(false);
            listView.enableLoadMore(true);
        }
    }
}
