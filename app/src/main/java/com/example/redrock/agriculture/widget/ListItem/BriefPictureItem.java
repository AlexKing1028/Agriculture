package com.example.redrock.agriculture.widget.ListItem;

import android.os.Parcel;

/**
 * Created by REDROCK on 12/30/2015.
 */
public class BriefPictureItem extends Item {
    public String info;
    public String imgSrc;

    BriefPictureItem(Parcel source) {
        super(source);
        info = source.readString();
        imgSrc = source.readString();
    }

    public BriefPictureItem(String title, String info, String imgSrc) {
        super(title);
        type = 2;
        this.info = info;
        this.imgSrc = imgSrc;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        type = 1;
        super.writeToParcel(dest, flags);
        dest.writeString(info);
        dest.writeString(imgSrc);
    }

}
