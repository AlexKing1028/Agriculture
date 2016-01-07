package com.example.redrock.agriculture.widget.ListItem;

import android.os.Parcel;

/**
 * Created by REDROCK on 12/30/2015.
 */
public class PictureBannerItem extends Item {
    int img_size;
    int titles_size;
    String[] imgSrcs;
    String[] titles;

    PictureBannerItem(Parcel source) {
        super(source);
        img_size = source.readInt();
        imgSrcs = new String[img_size];
        source.readStringArray(imgSrcs);
        titles_size = source.readInt();
        titles = new String[titles_size];
        source.readStringArray(titles);
    }

    public PictureBannerItem(String title, String[] imgSrcs, String[] titles) {
        super(title);
        type = 3;
        img_size = imgSrcs.length;
        this.imgSrcs = imgSrcs;
        titles_size = titles.length;
        this.titles = titles;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        type = 3;
        super.writeToParcel(dest, flags);
        dest.writeInt(img_size);
        dest.writeStringArray(imgSrcs);
        dest.writeInt(titles_size);
        dest.writeStringArray(titles);
    }
}
