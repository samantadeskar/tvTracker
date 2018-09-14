package com.example.samanta.tvtrackermvp.presenters;

import com.example.samanta.tvtrackermvp.firebase.database.DatabaseHelper;
import com.example.samanta.tvtrackermvp.firebase.database.DatabaseHelperImpl;
import com.example.samanta.tvtrackermvp.firebase.database.DatabaseUserCallback;
import com.example.samanta.tvtrackermvp.pojo.User;
import com.example.samanta.tvtrackermvp.ui.users.details.UserProfileView;

public class UserProfilePresenterImpl implements UserProfilePresenter {

    private UserProfileView view;
    private DatabaseHelper databaseHelper = new DatabaseHelperImpl();

    @Override
    public void setBaseView(UserProfileView view) {
        this.view = view;
    }

    @Override
    public void getData(final String userID) {

        databaseHelper.getUserInfo(new DatabaseUserCallback() {
            @Override
            public void onCallback(User user) {
                String username = user.getUsername();
                String profilePicture = user.getImage();
                String email = user.getEmail();
                String status = user.getStatus();
                view.setData(username,profilePicture,email,status);
            }
        }, userID);

    }
}
