package com.example.samanta.tvtrackermvp.ui.loginRegistration;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.example.samanta.tvtrackermvp.R;
import com.example.samanta.tvtrackermvp.firebase.authentication.AuthenticationHelper;
import com.example.samanta.tvtrackermvp.firebase.authentication.AuthenticationHelperImpl;
import com.example.samanta.tvtrackermvp.ui.movies.MoviesActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistrationActivity extends AppCompatActivity {

    AuthenticationHelper helper = new AuthenticationHelperImpl();
    @BindView(R.id.editTextUsername)
    EditText editTextUsername;
    @BindView(R.id.editTextEmail)
    EditText editTextEmail;
    @BindView(R.id.editTextPassword)
    EditText editTextPassword;
    @BindView(R.id.toolbarRegistration)
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);
        setUpToolbar();
    }

    public void setUpToolbar() {

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    @OnClick(R.id.buttonCreateAccount)
    public void registerUser() {

        String username = editTextUsername.getText().toString();
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        if (!TextUtils.isEmpty(username)
                && !TextUtils.isEmpty(email)
                && !TextUtils.isEmpty(password)) {
            if (password.length() >= 6) {
                helper.registerUser(email, username, password);
            } else {
                Toast.makeText(RegistrationActivity.this,
                        R.string.password_must_be_at_least_6_characters,
                        Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(RegistrationActivity.this,
                    R.string.fill_fields,
                    Toast.LENGTH_SHORT).show();
        }
        Intent intent = new Intent(RegistrationActivity.this, MoviesActivity.class);
        startActivity(intent);
        finish();
    }
}
