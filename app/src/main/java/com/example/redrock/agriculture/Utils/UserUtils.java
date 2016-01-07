package com.example.redrock.agriculture.Utils;

import com.android.volley.Response;
import com.example.redrock.agriculture.Model.UserModel;

/**
 * Created by REDROCK on 1/2/2016.
 */
public class UserUtils {
    static UserModel userModel = new UserModel(100, "test", "imageName");

    public static void fetchUserModel(Response.Listener listener){

    }

    public static UserModel getUserInfo(){
        return userModel;
    }
}
