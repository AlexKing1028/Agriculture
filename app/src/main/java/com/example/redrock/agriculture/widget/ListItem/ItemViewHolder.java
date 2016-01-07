package com.example.redrock.agriculture.widget.ListItem;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.redrock.agriculture.R;

/**
 * Created by REDROCK on 12/2/2015.
 */
public abstract class ItemViewHolder {
    final static int[] LAYOUT_RESOUCE_MAP = {R.layout.view_simple_item, R.layout.view_breif_picture_item, R.layout.view_banner};
    TextView title;

    public static ItemViewHolder getItemViewHolderFactory(int type) {
        switch (type) {
            case 2:
                return new BriefPictureViewHolder();
            case 3:
                return new PictureBannerViewHolder();
            default:
                return new SimpleItemViewHolder();
        }
    }

    public abstract View initialize(Item item, LayoutInflater inflater);

    public abstract void initialize(Item item);


}
