package com.example.redrock.agriculture.Tools;

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

    private Item(Parcel source) {
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

    public static class SimpleItem extends Item {
        public String info;

        private SimpleItem(Parcel source) {
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

    public static class BriefPictureItem extends Item {
        public String info;
        public String imgSrc;

        private BriefPictureItem(Parcel source) {
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

    public static class PictureBannerItem extends Item{
        int img_size;
        int titles_size;
        String[] imgSrcs;
        String[] titles;

        private PictureBannerItem(Parcel source) {
            super(source);
            img_size=source.readInt();
            imgSrcs=new String[img_size];
            source.readStringArray(imgSrcs);
            titles_size=source.readInt();
            titles=new String[titles_size];
            source.readStringArray(titles);
        }

        public PictureBannerItem(String title, String[] imgSrcs, String[] titles) {
            super(title);
            type = 3;
            img_size=imgSrcs.length;
            this.imgSrcs=imgSrcs;
            titles_size=titles.length;
            this.titles=titles;
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
}
