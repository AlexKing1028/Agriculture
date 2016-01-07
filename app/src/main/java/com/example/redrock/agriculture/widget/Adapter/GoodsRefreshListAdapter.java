package com.example.redrock.agriculture.widget.Adapter;

import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.example.redrock.agriculture.widget.LoadMoreListView;
import com.example.redrock.agriculture.widget.ListItem.BriefPictureItem;
import com.example.redrock.agriculture.widget.ListItem.Item;
import com.example.redrock.agriculture.widget.ListItem.ItemViewHolder;
import com.example.redrock.agriculture.widget.ListItem.SimpleItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by REDROCK on 12/30/2015.
 */
public class GoodsRefreshListAdapter extends LoadMoreListView.LoadMoreAdapter {
    GoodsResponseListener listener;
    public GoodsRefreshListAdapter(LayoutInflater inflater, LoadMoreListView listView){
        super(inflater, listView);
        listener=new GoodsResponseListener();
    }

    @Override
    public int getPageSize() {
        return 20;
    }

    @Override
    public void loadMoreData(int startCursor, int size) {
        new DataMockAsync(listener).execute(getPageSize());
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Item item=data.get(position);
        if (convertView == null){
            ItemViewHolder viewHolder=ItemViewHolder.getItemViewHolderFactory(item.type);
            convertView=viewHolder.initialize(item, inflater);
            convertView.setTag(viewHolder);
        }else {
            ItemViewHolder viewHolder=(ItemViewHolder)convertView.getTag();
            viewHolder.initialize(item);
        }
        return convertView;
    }

    final static int TYPE_COUNT=3;

    @Override
    public int getViewTypeCount(){
        return TYPE_COUNT;
    }

    @Override
    public int getItemViewType(int position){
        return data.get(position).type-1;
    }

    class DataMockAsync extends AsyncTask<Integer, Integer, List<Item>>{
        GoodsResponseListener listener;
        DataMockAsync(GoodsResponseListener listener){
            this.listener=listener;
        }

        @Override
        protected List<Item> doInBackground(Integer... params) {
            int size = params[0];
            List<Item> result= new ArrayList<>();
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
        protected void onPostExecute(List<Item> result){
            listener.onResponse(result);
        }
    }

    class GoodsResponseListener implements Response.Listener<List<Item>>{
        @Override
        public void onResponse(List<Item> response) {
            if (response == null){
                canLoadMore(false);
                return;
            }
            canLoadMore(response.size() != getPageSize());
            data.addAll(response);
            notifyDataSetChanged();
        }
    }
}

