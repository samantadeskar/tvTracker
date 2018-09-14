package com.example.samanta.tvtrackermvp.ui.myProfile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.samanta.tvtrackermvp.Constants.Constants;
import com.example.samanta.tvtrackermvp.R;
import com.example.samanta.tvtrackermvp.presenters.MyProfilePresenter;
import com.example.samanta.tvtrackermvp.presenters.MyProfilePresenterImpl;
import com.example.samanta.tvtrackermvp.ui.HeaderView;
import com.example.samanta.tvtrackermvp.ui.movies.MoviesActivity;
import com.example.samanta.tvtrackermvp.ui.tvShows.TvShowsActivity;
import com.example.samanta.tvtrackermvp.ui.users.UsersActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyProfileActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, MyProfileView {

    @BindView(R.id.toolbarProfile)
    Toolbar toolbarProfile;
    @BindView(R.id.navViewMyProfile)
    NavigationView navViewProfile;
    @BindView(R.id.imageViewProfilePicture)
    ImageView imageViewProfilePicture;
    @BindView(R.id.draweLayoutProfile)
    DrawerLayout drawerLayoutMyProfile;
    @BindView(R.id.editTextStatus)
    EditText editTextStatus;
    @BindView(R.id.editTextUsername)
    EditText editTextUsername;
    @BindView(R.id.editTextEmail)
    EditText editTextEmail;
    @BindView(R.id.textViewNumberOfWatchedEpisodes)
    TextView textViewNumberOfWatchedEpisodes;
    @BindView(R.id.textViewNumberOfWatchedMovies)
    TextView textViewNumberOfWatchedMovies;
    @BindView(R.id.imageButtonUploadStatus)
    ImageButton imageButtonUploadStatus;

    MyProfilePresenter presenter = new MyProfilePresenterImpl();


    private Uri profilePicURI;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        ButterKnife.bind(this);
        presenter.getData();
        presenter.setBaseView(this);
    }

    @Override
    public void setUpNavigationDrawer(String username, String profilePicture) {

        setSupportActionBar(toolbarProfile);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle
                (this, drawerLayoutMyProfile, toolbarProfile,
                        R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawerLayoutMyProfile.addDrawerListener(toggle);
        toggle.syncState();

        navViewProfile.setNavigationItemSelectedListener(this);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);


        View header = navViewProfile.getHeaderView(0);
        HeaderView headerViewHolder = new HeaderView(header, username, profilePicture);


    }

    public void onBackPressed() {

        if (drawerLayoutMyProfile.isDrawerOpen(GravityCompat.START)) {
            drawerLayoutMyProfile.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.nav_movies:
                startActivity(new Intent(this, MoviesActivity.class));
                finish();
                break;
            case R.id.nav_tvShows:
                startActivity(new Intent(this, TvShowsActivity.class));
                finish();
                break;
            case R.id.nav_watchlist:
                //startActivity(new Intent(this, WatchlistActivity.class));
                //finish();
            case R.id.nav_myProfile:
                startActivity(new Intent(this, MyProfileActivity.class));
                break;
            case R.id.nav_users:
                startActivity(new Intent(this, UsersActivity.class));
                break;
        }
        drawerLayoutMyProfile.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void setData(String username, String email, String status,
                        String profilePic) {

        editTextUsername.setText(username);
        editTextEmail.setText(email);
        editTextStatus.setText(status);

        Glide.with(this)
                .load(profilePic)
                .apply(RequestOptions.placeholderOf(R.drawable.noimage))
                .into(imageViewProfilePicture);

        editTextStatus.setEnabled(false);
        editTextUsername.setEnabled(false);
        editTextEmail.setEnabled(false);

    }

    @Override
    public void setNumberOfEpisodesAndMovies(String numberOfEpisodes, String numberOfMovies) {

        textViewNumberOfWatchedEpisodes.setText(numberOfEpisodes);
        textViewNumberOfWatchedMovies.setText(numberOfMovies);
    }

    @OnClick(R.id.imageViewProfilePicture)
    public void chooseProfilePic() {

        Intent gallery = new Intent();
        gallery.setAction(Intent.ACTION_GET_CONTENT);
        gallery.setType("image/*");
        startActivityForResult(gallery, Constants.GALLERY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constants.GALLERY && resultCode == RESULT_OK) {
            profilePicURI = data.getData();
            imageViewProfilePicture.setImageURI(profilePicURI);
            presenter.saveProfilePicture(profilePicURI);
        }
    }

    @OnClick(R.id.imageButtonEditInfo)
    public void editProfile() {

        editTextStatus.setEnabled(true);
        editTextUsername.setEnabled(true);
        editTextEmail.setEnabled(true);

        imageButtonUploadStatus.setVisibility(View.VISIBLE);


    }

    @OnClick(R.id.imageButtonUploadStatus)
    public void setUpdatedInfo() {
        String status = editTextStatus.getText().toString();
        String username = editTextUsername.getText().toString();
        String email = editTextEmail.getText().toString();

        if (!TextUtils.isEmpty(status) && !TextUtils.isEmpty(username)
                && !TextUtils.isEmpty(email)) {
            presenter.saveUpdatedInfo(username, email, status);
        }
        imageButtonUploadStatus.setVisibility(View.INVISIBLE);
    }
}
