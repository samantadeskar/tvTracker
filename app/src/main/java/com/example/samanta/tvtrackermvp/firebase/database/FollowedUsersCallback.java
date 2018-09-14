package com.example.samanta.tvtrackermvp.firebase.database;

import com.example.samanta.tvtrackermvp.pojo.User;

import java.util.List;

public interface FollowedUsersCallback {

    void onFollowedUsersCallback(List<User> followedUsers);
}
