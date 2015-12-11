package com.example.redrock.agriculture.NetTools.Deserialize;

import com.example.redrock.agriculture.Tools.Item;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by REDROCK on 12/11/2015.
 */
public class ItemListDeserializer extends Deserializer<ArrayList> {
    @Override
    public ArrayList deserialize(String json) {
        ArrayList<Item> list=new ArrayList<>();
        JsonParser parser = new JsonParser();
        JsonArray array = parser.parse(json).getAsJsonArray();
        Iterator<JsonElement> iterator = array.iterator();
        while (iterator.hasNext()) {
            JsonElement je = iterator.next();
            JsonObject jo = je.getAsJsonObject();
            int type=jo.get("type").getAsInt();
            switch (type){
                case 1:
                    list.add(gson.fromJson(je, Item.SimpleItem.class));
                    break;
                case 2:
                    list.add(gson.fromJson(je, Item.BriefPictureItem.class));
                    break;
                default:
                    list.add(gson.fromJson(je, Item.PictureBannerItem.class));
            }
        }
        return list;
    }
}
