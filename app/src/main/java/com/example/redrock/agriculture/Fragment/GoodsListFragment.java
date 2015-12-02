package com.example.redrock.agriculture.Fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.redrock.agriculture.Presenter.GoodsListPresenter;
import com.example.redrock.agriculture.Presenter.GoodsListPresenterImp;
import com.example.redrock.agriculture.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GoodsListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GoodsListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GoodsListFragment extends Fragment implements GoodsListPresenter.GoodsListView, SwipeRefreshLayout.OnRefreshListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private GoodsListPresenter goodsListPresenter;

    private ArrayAdapter<String> adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView listView;
    private Handler mhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    Bundle bundle=msg.getData();
                    setGoodsListData(bundle.getStringArrayList("data"), bundle.getInt("mode"));
                    adapter.notifyDataSetChanged();
                    swipeRefreshLayout.setRefreshing(false);
                    break;
                default:
            }
        }
    };

    @Override
    public void notifyDataChangeReady(ArrayList<String> data, int MODE){
        Message msg=new Message();
        Bundle bundle=new Bundle();
        bundle.putStringArrayList("data",data);
        bundle.putInt("mode",MODE);
        msg.setData(bundle);
        msg.what=1;
        mhandler.sendMessage(msg);
    }

    private void setGoodsListData(ArrayList<String> data, int MODE){
        switch (MODE){
            case 0:
                adapter.clear();
                adapter.addAll(data);
                break;
            case 1:
                adapter.addAll(data);
        }
    }



    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment goodsListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GoodsListFragment newInstance(String param1, String param2) {
        GoodsListFragment fragment = new GoodsListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public GoodsListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
         goodsListPresenter= new GoodsListPresenterImp(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_goods_list, container, false);
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipelayout_main_content);
        swipeRefreshLayout.setProgressViewEndTarget(true, 100);
        swipeRefreshLayout.setOnRefreshListener(this);
        listView = (ListView) rootView.findViewById(R.id.list_main_content);
        adapter=new ArrayAdapter<String>(this.getActivity(),R.layout.one_info_item,R.id.one_info_item_title,new ArrayList<String>());
        listView.setAdapter(adapter);
        onRefresh();
        return rootView;
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        goodsListPresenter.refreshData();
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
