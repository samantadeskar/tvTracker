package com.example.samanta.tvtrackermvp.presenters;

import com.example.samanta.tvtrackermvp.firebase.authentication.AuthenticationHelper;
import com.example.samanta.tvtrackermvp.firebase.authentication.AuthenticationHelperImpl;
import com.example.samanta.tvtrackermvp.firebase.database.AllUsersCallback;
import com.example.samanta.tvtrackermvp.firebase.database.DatabaseHelper;
import com.example.samanta.tvtrackermvp.firebase.database.DatabaseHelperImpl;
import com.example.samanta.tvtrackermvp.firebase.database.FollowUserCallback;
import com.example.samanta.tvtrackermvp.firebase.database.FollowedUsersCallback;
import com.example.samanta.tvtrackermvp.pojo.User;
import com.example.samanta.tvtrackermvp.ui.users.fragments.AllUsersView;

import java.util.List;

public class AllUsersPresenterImpl implements AllUsersPresenter {

    private AllUsersView view;
    private AuthenticationHelper authenticationHelper = new AuthenticationHelperImpl();
    private DatabaseHelper databaseHelper = new DatabaseHelperImpl();


    @Override
    public void setBaseView(AllUsersView view) {
        this.view = view;
    }

    @Override
    public void getAllUsers() {

        databaseHelper.getAllUsers(new AllUsersCallback() {
            @Override
            public void onAllUsersCallback(List<User> userList) {

                if(userList!=null){
                    getFollowedUsers(userList);
                }

            }
        });
    }

    @Override
    public void followUser(final User user) {

        String currentUserID = authenticationHelper.getCurrentUserID();

        databaseHelper.followUser(currentUserID, user, new FollowUserCallback() {
            @Override
            public void onFollowUserCallback(boolean isFollowed) {
                if(isFollowed){
                    view.toastFollowedUser(user.getUsername());
                }else {
                    view.toastFollowUser(user.getUsername());
                    getAllUsers();
                }
            }
        });

    }

    @Override
    public void getFollowedUsers(final List<User> allUsers) {

        String userID = authenticationHelper.getCurrentUserID();
        databaseHelper.getFollowedUsers(userID, new FollowedUsersCallback() {
            @Override
            public void onFollowedUsersCallback(List<User> followedUsers) {

                view.setUsers(allUsers,followedUsers);

            }
        });

    }
}
