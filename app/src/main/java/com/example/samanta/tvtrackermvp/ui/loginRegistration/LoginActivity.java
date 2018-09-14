package com.example.samanta.tvtrackermvp.ui.loginRegistration;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.samanta.tvtrackermvp.Constants.Constants;
import com.example.samanta.tvtrackermvp.R;
import com.example.samanta.tvtrackermvp.presenters.LoginPresenter;
import com.example.samanta.tvtrackermvp.presenters.LoginPresenterImpl;
import com.example.samanta.tvtrackermvp.ui.movies.MoviesActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginView {

    private LoginPresenter presenter = new LoginPresenterImpl();
    @BindView(R.id.editTextEmailLogin)
    EditText editTextEmailLogin;
    @BindView(R.id.editTextPasswordLogin)
    EditText editTextPasswordLogin;
    GoogleSignInClient googleSignInClient;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        presenter.setBaseView(this);
        initGoogle();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.checkIfUserLoggedIn(this);
    }

    public void initGoogle() {

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder
                (GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestIdToken(Constants.OAUTH_CLIENT_ID)
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, gso);

    }

    @Override
    public void setEmptyError() {
        Toast.makeText(LoginActivity.this, R.string.fill_fields,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void startMoviesActivity() {
        Intent intent = new Intent(LoginActivity.this, MoviesActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void setLoginError() {
        Toast.makeText(LoginActivity.this,
                getString(R.string.something_went_wrong),
                Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.buttonLogin)
    public void loginUser() {
        String email = editTextEmailLogin.getText().toString();
        String password = editTextPasswordLogin.getText().toString();
        presenter.loginUser(email, password, this);
    }

    @OnClick(R.id.textViewDontHaveAccount)
    public void goToRegister() {
        startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
    }

    @OnClick(R.id.buttonGoogleSignIn)
    public void loginWithGoogle() {

        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, Constants.RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constants.RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                Log.w("TAG", "Google sign in successful");
                GoogleSignInAccount account = task.getResult(ApiException.class);
                String token = account.getIdToken();
                presenter.firebaseAuthWithGoogle(account, this);
                finish();
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w("TAG", "Google sign in failed", e);
                // [START_EXCLUDE]
            }
        }
    }

}
