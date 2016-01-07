package com.example.redrock.agriculture.widget.ListItem;

import android.os.Parcel;

/**
 * Created by REDROCK on 12/30/2015.
 */
public class SimpleItem extends Item {
    public String info;

    SimpleItem(Parcel source) {
        super(source);
        info = source.readString();
    }

    public SimpleItem(String title, String info) {
        super(title);
        type = 1;
        this.info = info;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        type = 1;
        super.writeToParcel(dest, flags);
        dest.writeString(info);
    }
}
