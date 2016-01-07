package com.example.redrock.agriculture.Model;

/**
 * Created by REDROCK on 1/2/2016.
 */
public class UserModel {
    long userid;
    String username;
    String imageurl;

    public UserModel(long userid, String username, String imageurl) {
        this.userid = userid;
        this.username = username;
        this.imageurl = imageurl;
    }

    public String getUsername() {
        return username;
    }

    public String getImageurl() {
        return imageurl;
    }
}
