package com.example.samanta.tvtrackermvp.presenters;

import android.content.Context;
import android.text.TextUtils;

import com.example.samanta.tvtrackermvp.firebase.authentication.AuthenticationHelper;
import com.example.samanta.tvtrackermvp.firebase.authentication.AuthenticationHelperImpl;
import com.example.samanta.tvtrackermvp.ui.loginRegistration.LoginView;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginPresenterImpl implements LoginPresenter {

    LoginView view;
    AuthenticationHelper helper = new AuthenticationHelperImpl();

    @Override
    public void setBaseView(LoginView view) {
        this.view = view;
    }

    @Override
    public void loginUser(String email, String password, Context context) {
        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
            helper.loginUser(email, password);
        } else {
            view.setEmptyError();
        }
        checkIfUserLoggedIn(context);
    }

    @Override
    public void checkIfUserLoggedIn(Context context) {

        Boolean isLoggedIn = helper.checkIfUserIsLoggedIn();
        Boolean isLoggedInWithGoogle = helper.checkIfUserIsLoggedInWithGoogle(context);


        if (isLoggedIn) {
            view.startMoviesActivity();
        } else if (isLoggedInWithGoogle) {
            view.startMoviesActivity();
        } else {
            view.setLoginError();
        }
    }


    public void firebaseAuthWithGoogle(GoogleSignInAccount account, Context context) {

        String token = account.getIdToken();
        String displayName = account.getDisplayName();
        String email = account.getEmail();

        AuthCredential credential = GoogleAuthProvider.getCredential(token, null);
        helper.logInWithGoogleCredentials(credential, displayName, email);
        Boolean isLoggedIn = helper.checkIfUserIsLoggedInWithGoogle(context);
        if (isLoggedIn) {
            view.startMoviesActivity();
        }

    }


}
