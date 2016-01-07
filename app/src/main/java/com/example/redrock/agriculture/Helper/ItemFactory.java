package com.example.redrock.agriculture.Helper;

import com.example.redrock.agriculture.widget.ListItem.Item;

import org.json.JSONObject;

/**
 * Created by REDROCK on 12/9/2015.
 */
public class ItemFactory {
    public static Item getItem(JSONObject jo) throws Exception{
        int type=jo.getInt("type");
        switch (type){
            case 1:

        }
        return null;
    }
}
