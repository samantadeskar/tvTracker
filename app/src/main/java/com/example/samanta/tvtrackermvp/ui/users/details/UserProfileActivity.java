package com.example.samanta.tvtrackermvp.ui.users.details;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.samanta.tvtrackermvp.Constants.Constants;
import com.example.samanta.tvtrackermvp.R;
import com.example.samanta.tvtrackermvp.presenters.UserProfilePresenter;
import com.example.samanta.tvtrackermvp.presenters.UserProfilePresenterImpl;
import com.example.samanta.tvtrackermvp.ui.users.details.watchlist.UserWatchlistActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserProfileActivity extends AppCompatActivity
        implements UserProfileView {

    private UserProfilePresenter presenter = new UserProfilePresenterImpl();

    @BindView(R.id.imageViewUserImage)
    ImageView imageViewUserImage;
    @BindView(R.id.textViewStatus)
    TextView textViewStatus;
    @BindView(R.id.textViewUserProfileUsername)
    TextView textViewUsername;
    @BindView(R.id.textViewUserEmail)
    TextView textViewUserEmail;
    @BindView(R.id.toolbarUserProfile)
    Toolbar toolbarUserProfile;

    Intent intent;
    Bundle extras;

    String userID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        ButterKnife.bind(this);

        intent = getIntent();
        extras = intent.getExtras();

        userID = extras.getString(Constants.USER_ID);

        presenter.setBaseView(this);
        presenter.getData(userID);

        setSupportActionBar(toolbarUserProfile);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void setData(String username, String profilePicture,
                        String email, String status) {

        textViewUsername.setText(username);
        textViewStatus.setText(status);
        textViewUserEmail.setText(email);
        Glide.with(this)
                .load(profilePicture)
                .apply(RequestOptions.placeholderOf(R.drawable.noimage))
                .into(imageViewUserImage);

    }

    @OnClick(R.id.imageButtonSeeUserWatchlist)
    public void seeUserWatchlist() {
        Intent intent = new Intent(UserProfileActivity.this, UserWatchlistActivity.class);
        Bundle extras = new Bundle();
        extras.putString(Constants.USER_ID, userID);
        intent.putExtras(extras);
        startActivity(intent);
    }
}
