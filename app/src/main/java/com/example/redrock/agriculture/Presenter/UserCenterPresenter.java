package com.example.redrock.agriculture.Presenter;

import android.os.AsyncTask;

import com.example.redrock.agriculture.Model.UserModel;

/**
 * Created by REDROCK on 1/2/2016.
 */
public class UserCenterPresenter {
    private UserCenterView view;

    public void attachView(UserCenterView view) {
        this.view = view;
    }

    public void detachView() {
        this.view = null;
    }

    public void init() {
        new loginAsync().execute(false);
    }

    public interface UserCenterView {
        void renderNoLogin();

        void renderLogin(String username);
    }

    class loginAsync extends AsyncTask<Boolean, Integer, UserModel> {
        @Override
        protected UserModel doInBackground(Boolean... params) {
            if (params[0]) {
                return new UserModel(-1, "swl", "/image/aabc");
            }
            return null;
        }

        @Override
        protected void onPostExecute(UserModel u) {
            if (null == u) {
                view.renderNoLogin();
            } else {
                view.renderLogin(u.getUsername());
            }
        }
    }
}
