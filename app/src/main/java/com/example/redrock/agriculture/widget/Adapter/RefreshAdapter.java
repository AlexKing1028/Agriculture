package com.example.redrock.agriculture.widget.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.redrock.agriculture.widget.ListItem.Item;
import com.example.redrock.agriculture.widget.ListItem.ItemViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by REDROCK on 12/2/2015.
 */
public class RefreshAdapter extends BaseAdapter{
    List<Item> data;
    LayoutInflater inflater;

    public RefreshAdapter(LayoutInflater inflater){
        data=new ArrayList<>();
        this.inflater=inflater;
    }

    public void clear(){
        data.clear();
    }

    public void addAll(List<Item> array){
        data.addAll(array);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
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

    @Override
    public int getViewTypeCount(){
        return TYPE_COUNT;
    }

    @Override
    public int getItemViewType(int position){
        return data.get(position).type-1;
    }

    final static int TYPE_COUNT=3;
}
