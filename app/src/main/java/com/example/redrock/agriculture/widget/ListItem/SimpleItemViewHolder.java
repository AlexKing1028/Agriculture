package com.example.redrock.agriculture.widget.ListItem;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.redrock.agriculture.R;

/**
 * Created by REDROCK on 12/30/2015.
 */
public class SimpleItemViewHolder extends ItemViewHolder {
    TextView info;

    @Override
    public void initialize(Item item) {
        if (null != title) {
            title.setText(item.title);
        }
        /**
         * todo...setting view data
         */
        if (item instanceof SimpleItem) {
            SimpleItem simpleItem = (SimpleItem) item;
            info.setText(simpleItem.info);
        }
    }

    @Override
    public View initialize(Item item, LayoutInflater inflater) {
        View convertView = inflater.inflate(LAYOUT_RESOUCE_MAP[item.type - 1], null);
        title = (TextView) convertView.findViewById(R.id.simple_item_title);
        /**
         * todo...binding
         */
        info = (TextView) convertView.findViewById(R.id.simple_item_info);
        initialize(item);
        return convertView;
    }
}
