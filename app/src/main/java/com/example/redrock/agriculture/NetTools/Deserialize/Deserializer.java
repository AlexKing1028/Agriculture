package com.example.redrock.agriculture.NetTools.Deserialize;

import com.google.gson.Gson;

/**
 * Created by REDROCK on 12/11/2015.
 */
public abstract class Deserializer<T> {
    protected final Gson gson=new Gson();
    public abstract T deserialize(String json);
}
