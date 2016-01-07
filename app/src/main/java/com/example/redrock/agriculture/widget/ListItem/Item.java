package com.example.redrock.agriculture.widget.ListItem;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by REDROCK on 12/2/2015.
 */
public class Item implements Parcelable {
    public static final Parcelable.ClassLoaderCreator<Item> CREATOR = new ClassLoaderCreator<Item>() {
        @Override
        public Item createFromParcel(Parcel source) {
            return new Item(source);
        }

        @Override
        public Item createFromParcel(Parcel source, ClassLoader loader) {
            int type = source.readInt();
            switch (type) {
                case 2:
                    return new BriefPictureItem(source);
                case 3:
                    return new PictureBannerItem(source);
                default:
                    return new SimpleItem(source);
            }
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };
    public int type;
    public String title;

    public Item(String title) {
        this.title = title;
    }

    public Item(Parcel source) {
        title = source.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(type);
        dest.writeString(title);
    }

}
