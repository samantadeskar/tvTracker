package com.example.samanta.tvtrackermvp.presenters;

import android.content.Context;

import com.example.samanta.tvtrackermvp.firebase.authentication.AuthenticationHelper;
import com.example.samanta.tvtrackermvp.firebase.authentication.AuthenticationHelperImpl;
import com.example.samanta.tvtrackermvp.firebase.database.DatabaseHelper;
import com.example.samanta.tvtrackermvp.firebase.database.DatabaseHelperImpl;
import com.example.samanta.tvtrackermvp.firebase.database.DatabaseUserCallback;
import com.example.samanta.tvtrackermvp.pojo.User;
import com.example.samanta.tvtrackermvp.ui.tvShows.TvShowsView;

public class TvShowsPresenterImpl implements TvShowsPresenter {

    TvShowsView view;
    AuthenticationHelper authenticationHelper = new AuthenticationHelperImpl();
    DatabaseHelper databaseHelper = new DatabaseHelperImpl();

    @Override
    public void setBaseView(TvShowsView view) {
        this.view = view;
    }

    @Override
    public void signoutUser(Context context) {

    /*    authenticationHelper.logoutUser(context);

        Boolean isUserLoggedIn = authenticationHelper.checkIfUserIsLoggedIn();
        if (!isUserLoggedIn) {
            view.setLoginActivity();
        } else {
            view.setLogoutError();
        }*/
    }

    @Override
    public void getUserInfo() {

        databaseHelper.getUserInfo(new DatabaseUserCallback() {
            @Override
            public void onCallback(User user) {
                String username = user.getUsername();
                String image = user.getImage();
                view.setUpNavigationDrawer(username, image);
            }
        }, authenticationHelper.getCurrentUserID());
    }
}