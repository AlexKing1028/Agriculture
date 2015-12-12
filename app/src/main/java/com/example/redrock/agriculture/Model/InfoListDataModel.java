package com.example.redrock.agriculture.Model;

import com.android.volley.Response;
import com.example.redrock.agriculture.Const.CommenConst;
import com.example.redrock.agriculture.NetTools.Deserialize.ItemListDeserializer;
import com.example.redrock.agriculture.NetTools.GsonDeserializeRequest;
import com.example.redrock.agriculture.NetTools.SingletonRequestQueue;
import com.example.redrock.agriculture.R;
import com.example.redrock.agriculture.Tools.ContextUtil;
import com.example.redrock.agriculture.Tools.Item;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by REDROCK on 11/29/2015.
 */
public class InfoListDataModel {
    public void fetchInfoData(final OnFetchListener onFetchListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<Item> result = new ArrayList<>();

                String[] imgsrcs = {"R.drawable.jiulaba", "R.drawable.kakaxi", "R.drawable.android_structue"};
                String[] titles = {"1", "2", "3"};
                Item.PictureBannerItem pictureBannerItem = new Item.PictureBannerItem("banner", imgsrcs, titles);
                result.add(pictureBannerItem);


                Random random = new Random();
                int i = random.nextInt(1000);
                for (int j = 0; j < 50; j++) {
                    int tmp = random.nextInt() % 5;
                    if (tmp == 1) {
                        String title = "title" + j + i;
                        String info = "info" + j;
                        Item.SimpleItem simpleItem = new Item.SimpleItem(title, info);
                        result.add(simpleItem);
                    } else if (tmp == 2) {
                        String title = "title pic" + j + i;
                        String info = "this" + i + " is info" + j;
                        Item.BriefPictureItem briefPictureItem = new Item.BriefPictureItem(title, info, "R.drawable.jiulaba");
                        result.add(briefPictureItem);
                    } else if (tmp == 3) {
                        String title = "title pic" + j + i;
                        String info = "this" + i + " is info" + j;
                        Item.BriefPictureItem briefPictureItem = new Item.BriefPictureItem(title, info, "R.drawable.kakaxi");
                        result.add(briefPictureItem);
                    }
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                onFetchListener.onFetch(result);
            }
        }).start();
    }

    public void fetchDataFromInternet(final OnFetchListener onFetchListener) {
        SingletonRequestQueue.getInstance(ContextUtil.getInstance()).
                addToRequestQueue(new GsonDeserializeRequest<ArrayList>(CommenConst.HOST + "/info", new ItemListDeserializer(), new Response.Listener<ArrayList>() {
                    @Override
                    public void onResponse(ArrayList response) {
                        onFetchListener.onFetch(response);
                    }
                }, null));

    }

    public interface OnFetchListener {
        void onFetch(ArrayList<Item> data);
    }

    ;
}
