package com.example.samanta.tvtrackermvp.firebase.database;

import com.example.samanta.tvtrackermvp.pojo.User;

import java.util.List;

public interface AllUsersCallback {

    void onAllUsersCallback(List<User> userList);

}
