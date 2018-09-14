package com.example.samanta.tvtrackermvp.presenters;

import com.example.samanta.tvtrackermvp.firebase.authentication.AuthenticationHelper;
import com.example.samanta.tvtrackermvp.firebase.authentication.AuthenticationHelperImpl;
import com.example.samanta.tvtrackermvp.firebase.database.DatabaseHelper;
import com.example.samanta.tvtrackermvp.firebase.database.DatabaseHelperImpl;
import com.example.samanta.tvtrackermvp.firebase.database.DatabaseUserCallback;
import com.example.samanta.tvtrackermvp.pojo.User;
import com.example.samanta.tvtrackermvp.ui.movies.MoviesView;

public class MoviesPresenterImpl implements MoviesPresenter {

    MoviesView view;
    private AuthenticationHelper authenticationHelper = new AuthenticationHelperImpl();
    private DatabaseHelper databaseHelper = new DatabaseHelperImpl();

    @Override
    public void setBaseView(MoviesView view) {
        this.view = view;
    }

    @Override
    public void getData() {
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
