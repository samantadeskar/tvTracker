package com.example.samanta.tvtrackermvp.presenters;

import com.example.samanta.tvtrackermvp.firebase.authentication.AuthenticationHelper;
import com.example.samanta.tvtrackermvp.firebase.authentication.AuthenticationHelperImpl;
import com.example.samanta.tvtrackermvp.firebase.database.DatabaseHelper;
import com.example.samanta.tvtrackermvp.firebase.database.DatabaseHelperImpl;
import com.example.samanta.tvtrackermvp.firebase.database.FollowedUsersCallback;
import com.example.samanta.tvtrackermvp.firebase.database.UnfollowUserCallback;
import com.example.samanta.tvtrackermvp.pojo.User;
import com.example.samanta.tvtrackermvp.ui.users.fragments.FollowedUsersView;

import java.util.List;

public class FollowedUsersPresenterImpl implements FollowedUsersPresenter {

    private FollowedUsersView view;
    private AuthenticationHelper authenticationHelper = new AuthenticationHelperImpl();
    private DatabaseHelper databaseHelper = new DatabaseHelperImpl();

    @Override

    public void setBaseView(FollowedUsersView view) {
        this.view = view;
    }

    @Override
    public void getFollowedUsers() {

        String userID = authenticationHelper.getCurrentUserID();
        databaseHelper.getFollowedUsers(userID, new FollowedUsersCallback() {
            @Override
            public void onFollowedUsersCallback(List<User> followedUsers) {
                view.setFollowedUsers(followedUsers);
            }
        });

    }

    @Override
    public void unfollowUser(final User user) {
        String userID = authenticationHelper.getCurrentUserID();
        databaseHelper.unfollowUser(user, userID, new UnfollowUserCallback() {
            @Override
            public void onUnfollowUserCallback(boolean isUnfollowed) {
                if(isUnfollowed){
                    view.toastUnfollowed(user.getUsername());
                    getFollowedUsers();
                }
                else {
                    view.toastUnfollowError(user.getUsername());
                }
            }
        });
    }
}
