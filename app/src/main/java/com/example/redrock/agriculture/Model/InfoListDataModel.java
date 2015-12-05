package com.example.redrock.agriculture.Model;

import com.example.redrock.agriculture.R;
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
                        Item.BreifPictureItem breifPictureItem = new Item.BreifPictureItem(title, info, R.drawable.jiulaba);
                        result.add(breifPictureItem);
                    } else if (tmp == 3) {
                        String title = "title pic" + j + i;
                        String info = "this" + i + " is info" + j;
                        Item.BreifPictureItem breifPictureItem = new Item.BreifPictureItem(title, info, R.drawable.kakaxi);
                        result.add(breifPictureItem);
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

    public interface OnFetchListener {
        void onFetch(ArrayList<Item> data);
    }

    ;
}
