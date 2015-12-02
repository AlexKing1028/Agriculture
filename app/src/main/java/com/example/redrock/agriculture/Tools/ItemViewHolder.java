package com.example.redrock.agriculture.Tools;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.redrock.agriculture.R;

/**
 * Created by REDROCK on 12/2/2015.
 */
public abstract class ItemViewHolder{
    final static int[] LAYOUT_RESOUCE_MAP={R.layout.view_simple_item};
    TextView title;

    static class SimpleItemViewHolder extends ItemViewHolder{
        TextView info;

        @Override
        public void initalize(Item item) {
            if (null != title){
                title.setText(item.title);
            }
            /**
             * todo...setting view data
             */
            if (item instanceof Item.SimpleItem){
                Item.SimpleItem simpleItem=(Item.SimpleItem)item;
                info.setText(simpleItem.info);
            }
        }

        public View initalize(Item item, LayoutInflater inflater){
            View convertView=inflater.inflate(LAYOUT_RESOUCE_MAP[item.type], null);
            title=(TextView)convertView.findViewById(R.id.item_title);
            /**
             * todo...binding
             */
            info=(TextView)convertView.findViewById(R.id.item_info);
            initalize(item);
            return convertView;
        }
    }
    public abstract View initalize(Item item, LayoutInflater inflater);
    public abstract void initalize(Item item);

    public final static ItemViewHolder getItemViewHolderFactory(int type){
        switch (type){
            case 0:
                return new ItemViewHolder.SimpleItemViewHolder();
        }
        return null;    }
}
