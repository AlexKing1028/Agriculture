package com.example.redrock.agriculture.Tools;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.redrock.agriculture.R;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;

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

    public static class PictureBannerViewHolder extends ItemViewHolder {
        ViewPager viewPager;
        CirclePageIndicator circlePageIndicator;

        @Override
        public View initialize(Item item, LayoutInflater inflater) {
            View convertView = inflater.inflate(R.layout.view_banner, null);
            viewPager = (ViewPager) convertView.findViewById(R.id.banner_viewpager);
            circlePageIndicator = (CirclePageIndicator) convertView.findViewById(R.id.banner_indicator);
            initialize(item);
            return convertView;
        }

        @Override
        public void initialize(Item item) {
            if (item instanceof Item.PictureBannerItem) {
                Item.PictureBannerItem pictureBannerItem = (Item.PictureBannerItem) item;
                ArrayList<View> views = new ArrayList<>();
                int[] imgSrcs = pictureBannerItem.imgSrcs;
                for (int srcid : imgSrcs) {
                    ImageView view_tmp = new ImageView(viewPager.getContext());
                    view_tmp.setImageResource(srcid);
                    view_tmp.setScaleType(ImageView.ScaleType.FIT_XY);
                    views.add(view_tmp);
                }
                BannerViewPagerAdapter bannerViewPagerAdapter = new BannerViewPagerAdapter(views);
                viewPager.setAdapter(bannerViewPagerAdapter);

                circlePageIndicator.setViewPager(viewPager);
            }
        }

        private class BannerViewPagerAdapter extends PagerAdapter {
            ArrayList<View> viewList;

            public BannerViewPagerAdapter(ArrayList<View> viewList) {
                this.viewList = viewList;
            }

            @Override
            public int getCount() {
                return viewList == null ? 0 : viewList.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                ((ViewPager) container).removeView(viewList.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ((ViewPager) container).addView(viewList.get(position));
                return viewList.get(position);
            }

            @Override
            public int getItemPosition(Object object) {
                return super.getItemPosition(object);
            }

        }
    }

    public static class BriefPictureViewHolder extends ItemViewHolder {
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
            if (item instanceof Item.BriefPictureItem) {
                Item.BriefPictureItem briefPictureItem = (Item.BriefPictureItem) item;
                if (pic != null) pic.setImageResource(briefPictureItem.imgSrc);
                if (info != null) info.setText(briefPictureItem.info);
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
