package com.example.redrock.agriculture.Model;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by REDROCK on 11/29/2015.
 */
public class InfoListDataModel {
    public interface OnFetchListener {
        void onFetch(ArrayList<String> data);
    }

    public void fetchInfoData(final OnFetchListener onFetchListener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<String> result = new ArrayList<>();
                int i = new Random().nextInt(1000);
                for (int j = 0; j < 20; j++) {
                    result.add("this is the " + (i + j));
                }
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                onFetchListener.onFetch(result);
            }
        }).start();
    };
}
