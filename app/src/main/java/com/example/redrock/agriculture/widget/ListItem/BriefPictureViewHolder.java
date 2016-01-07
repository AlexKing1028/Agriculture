package com.example.redrock.agriculture.widget.ListItem;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.example.redrock.agriculture.NetTools.SingletonRequestQueue;
import com.example.redrock.agriculture.R;
import com.example.redrock.agriculture.Utils.ContextUtil;

/**
 * Created by REDROCK on 12/30/2015.
 */
public class BriefPictureViewHolder extends ItemViewHolder {
    NetworkImageView pic;
    TextView info;

    @Override
    public View initialize(Item item, LayoutInflater inflater) {
        View convertView = inflater.inflate(LAYOUT_RESOUCE_MAP[item.type - 1], null);
        title = (TextView) convertView.findViewById(R.id.breif_picture_item_title);
        info = (TextView) convertView.findViewById(R.id.breif_picture_item_info);
        pic = (NetworkImageView) convertView.findViewById(R.id.breif_picture_item_pic);
        initialize(item);
        return convertView;
    }

    @Override
    public void initialize(Item item) {
        if (null != title) {
            title.setText(item.title);
        }
        if (item instanceof BriefPictureItem) {
            BriefPictureItem briefPictureItem = (BriefPictureItem) item;
            if (pic != null) {
                pic.setDefaultImageResId(R.drawable.loading);
                pic.setErrorImageResId(R.drawable.error);
                pic.setImageUrl(briefPictureItem.imgSrc,
                        SingletonRequestQueue.getInstance(ContextUtil.getInstance()).getImageLoader());
            }
            if (info != null) info.setText(briefPictureItem.info);
        }
    }
}
