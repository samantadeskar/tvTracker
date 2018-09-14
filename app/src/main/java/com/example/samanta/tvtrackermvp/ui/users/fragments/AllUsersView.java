package com.example.samanta.tvtrackermvp.ui.users.fragments;

import com.example.samanta.tvtrackermvp.pojo.User;

import java.util.List;

public interface AllUsersView {

    void setUsers(List<User> userList, List<User> followedUserList);

    void toastFollowUser(String username);

    void toastFollowedUser(String username);
}
