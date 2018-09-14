package com.example.samanta.tvtrackermvp.presenters;

import com.example.samanta.tvtrackermvp.firebase.database.DatabaseHelper;
import com.example.samanta.tvtrackermvp.firebase.database.DatabaseHelperImpl;
import com.example.samanta.tvtrackermvp.firebase.database.DatabaseUserCallback;
import com.example.samanta.tvtrackermvp.pojo.User;
import com.example.samanta.tvtrackermvp.ui.users.details.watchlist.UserWatchlistView;

public class UserWatchlistPresenterImpl implements UserWatchlistPresenter {

    UserWatchlistView view;
    private DatabaseHelper databaseHelper = new DatabaseHelperImpl();


    @Override
    public void setBaseView(UserWatchlistView view) {
        this.view = view;
    }

    @Override
    public void getData(String userID) {

        databaseHelper.getUserInfo(new DatabaseUserCallback() {
            @Override
            public void onCallback(User user) {
                String username = user.getUsername();
                String image = user.getImage();
                view.setUpNavigationDrawer(username, image);
            }
        }, userID);

    }


}
