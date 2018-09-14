package com.example.samanta.tvtrackermvp.presenters;

import com.example.samanta.tvtrackermvp.ui.users.details.UserProfileView;

public interface UserProfilePresenter {

    void setBaseView(UserProfileView view);

    void getData(String userID);
}
