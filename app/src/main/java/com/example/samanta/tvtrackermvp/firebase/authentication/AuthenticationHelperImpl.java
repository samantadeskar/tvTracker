package com.example.samanta.tvtrackermvp.firebase.authentication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.samanta.tvtrackermvp.firebase.database.DatabaseHelper;
import com.example.samanta.tvtrackermvp.firebase.database.DatabaseHelperImpl;
import com.example.samanta.tvtrackermvp.pojo.User;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AuthenticationHelperImpl implements AuthenticationHelper {

    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private DatabaseHelper databaseHelper = new DatabaseHelperImpl();
    private GoogleSignInClient googleSignInClient;


    @Override
    public void loginUser(String email, String password) {

        String userEmail = email;
        String userPassword = password;

        auth.signInWithEmailAndPassword(userEmail, userPassword)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        if (authResult != null) {
                            checkIfUserIsLoggedIn();
                        }
                    }
                });
    }

    @Override
    public void registerUser(final String email,
                             final String username,
                             final String password) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        if (authResult != null) {
                            String userID = auth.getCurrentUser().getUid();
                            User user = new User();
                            user.setEmail(email);
                            user.setUsername(username);
                            user.setPassword(password);
                            user.setUserID(userID);
                            databaseHelper.saveUser(user);
                        }
                    }
                });
    }

    @Override
    public void logoutUser(Context context) {

        boolean isLoggedIn = checkIfUserIsLoggedIn();
        boolean isLoggedInWithGoogle = checkIfUserIsLoggedInWithGoogle(context);

        if (isLoggedIn) {
            auth.signOut();
        } else if (isLoggedInWithGoogle) {
            googleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    checkIfUserIsLoggedIn();
                }
            });
        }

    }

    @Override
    public boolean checkIfUserIsLoggedIn() {
        FirebaseUser currentUser = auth.getCurrentUser();
        if (currentUser != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean checkIfUserIsLoggedInWithGoogle(Context context) {

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(context);
        if (account != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void logInWithGoogleCredentials(final AuthCredential credential,
                                           final String displayName,
                                           final String email) {
        auth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "signInWithCredential:success");
                            User user = new User();
                            user.setUserID(auth.getCurrentUser().getUid());
                            user.setUsername(displayName);
                            user.setEmail(email);
                            databaseHelper.saveUser(user);
                        }
                    }
                });
    }

    @Override
    public String getCurrentUserID() {

        FirebaseUser user = auth.getCurrentUser();

        if (user!=null){
            return user.getUid();
        }
        else {
            return null;
        }
    }
}
