package com.example.redrock.agriculture.Tools;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.redrock.agriculture.R;

/**
 * Created by REDROCK on 12/2/2015.
 */
public abstract class ItemViewHolder {
    final static int[] LAYOUT_RESOUCE_MAP = {R.layout.view_simple_item, R.layout.view_breif_picture_item};
    TextView title;

    public static ItemViewHolder getItemViewHolderFactory(int type) {
        switch (type) {
            case 2:
                return new BreifPictureViewHolder();
            default:
                return new SimpleItemViewHolder();
        }
    }

    public abstract View initialize(Item item, LayoutInflater inflater);

    public abstract void initialize(Item item);

    public static class BreifPictureViewHolder extends ItemViewHolder {
        ImageView pic;
        TextView info;

        @Override
        public View initialize(Item item, LayoutInflater inflater) {
            View convertView = inflater.inflate(LAYOUT_RESOUCE_MAP[item.type - 1], null);
            title = (TextView) convertView.findViewById(R.id.breif_picture_item_title);
            info = (TextView) convertView.findViewById(R.id.breif_picture_item_info);
            pic = (ImageView) convertView.findViewById(R.id.breif_picture_item_pic);
            initialize(item);
            return convertView;
        }

        @Override
        public void initialize(Item item) {
            if (null != title) {
                title.setText(item.title);
            }
            if (item instanceof Item.BreifPictureItem) {
                Item.BreifPictureItem breifPictureItem = (Item.BreifPictureItem) item;
                if (pic != null) pic.setImageResource(breifPictureItem.imgSrc);
                if (info != null) info.setText(breifPictureItem.info);
            }
        }
    }


    public static class SimpleItemViewHolder extends ItemViewHolder {
        TextView info;

        @Override
        public void initialize(Item item) {
            if (null != title) {
                title.setText(item.title);
            }
            /**
             * todo...setting view data
             */
            if (item instanceof Item.SimpleItem) {
                Item.SimpleItem simpleItem = (Item.SimpleItem) item;
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
}
