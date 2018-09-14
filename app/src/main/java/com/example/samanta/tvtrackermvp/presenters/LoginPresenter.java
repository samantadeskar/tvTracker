package com.example.samanta.tvtrackermvp.presenters;

import android.content.Context;
import android.content.Intent;

import com.example.samanta.tvtrackermvp.ui.loginRegistration.LoginView;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public interface LoginPresenter {

    void setBaseView(LoginView view);
    void loginUser(String email, String password, Context context);
    void checkIfUserLoggedIn(Context context);
    void firebaseAuthWithGoogle(GoogleSignInAccount account, Context context);
}
