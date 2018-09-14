package com.example.samanta.tvtrackermvp.presenters;

import com.example.samanta.tvtrackermvp.pojo.User;
import com.example.samanta.tvtrackermvp.ui.users.fragments.AllUsersView;

import java.util.List;

public interface AllUsersPresenter {
    void setBaseView(AllUsersView view);

    void getAllUsers();

    void followUser(User user);

    void getFollowedUsers(List<User> allUsers);


}
