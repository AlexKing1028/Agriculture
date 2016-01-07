package com.example.redrock.agriculture.widget.ListItem;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.toolbox.NetworkImageView;
import com.example.redrock.agriculture.NetTools.SingletonRequestQueue;
import com.example.redrock.agriculture.R;
import com.example.redrock.agriculture.Utils.ContextUtil;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;

/**
 * Created by REDROCK on 12/30/2015.
 */
public class PictureBannerViewHolder extends ItemViewHolder {
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
        if (item instanceof PictureBannerItem) {
            PictureBannerItem pictureBannerItem = (PictureBannerItem) item;
            ArrayList<View> views = new ArrayList<>();
            String[] imgSrcs = pictureBannerItem.imgSrcs;
            for (String imgSrc : imgSrcs) {
                NetworkImageView view_tmp = new NetworkImageView(viewPager.getContext());
                view_tmp.setScaleType(ImageView.ScaleType.FIT_XY);
                view_tmp.setDefaultImageResId(R.drawable.loading);
                view_tmp.setErrorImageResId(R.drawable.error);
                view_tmp.setImageUrl(imgSrc,
                        SingletonRequestQueue.getInstance(ContextUtil.getInstance()).getImageLoader());
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
