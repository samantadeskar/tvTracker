package com.example.samanta.tvtrackermvp.ui.users.fragments;

import com.example.samanta.tvtrackermvp.pojo.User;

import java.util.List;

public interface FollowedUsersView {

    void setFollowedUsers(List<User> usersList);

    void toastUnfollowed(String username);

    void toastUnfollowError(String username);
}
