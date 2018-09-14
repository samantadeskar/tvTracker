package com.example.samanta.tvtrackermvp.firebase.authentication;

import android.content.Context;

import com.example.samanta.tvtrackermvp.pojo.User;
import com.google.firebase.auth.AuthCredential;

public interface AuthenticationHelper {

    void loginUser(String email, String password);

    void registerUser(String email, String username, String password);

    void logoutUser(Context context);

    boolean checkIfUserIsLoggedIn();

    boolean checkIfUserIsLoggedInWithGoogle(Context context);

    void logInWithGoogleCredentials(AuthCredential credential, String displayName, String email);

    String getCurrentUserID();

}
