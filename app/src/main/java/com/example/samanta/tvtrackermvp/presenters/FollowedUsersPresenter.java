package com.example.samanta.tvtrackermvp.presenters;

import com.example.samanta.tvtrackermvp.pojo.User;
import com.example.samanta.tvtrackermvp.ui.users.fragments.FollowedUsersView;

public interface FollowedUsersPresenter {
    void setBaseView(FollowedUsersView view);

    void getFollowedUsers();

    void unfollowUser(User user);
}
