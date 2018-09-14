package com.example.samanta.tvtrackermvp.ui.users.details.watchlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.example.samanta.tvtrackermvp.Constants.Constants;
import com.example.samanta.tvtrackermvp.R;
import com.example.samanta.tvtrackermvp.presenters.UserWatchlistPresenter;
import com.example.samanta.tvtrackermvp.presenters.UserWatchlistPresenterImpl;
import com.example.samanta.tvtrackermvp.ui.HeaderView;
import com.example.samanta.tvtrackermvp.ui.movies.MoviesActivity;
import com.example.samanta.tvtrackermvp.ui.myProfile.MyProfileActivity;
import com.example.samanta.tvtrackermvp.ui.tvShows.TvShowsActivity;
import com.example.samanta.tvtrackermvp.ui.users.UsersActivity;
import com.example.samanta.tvtrackermvp.ui.users.details.watchlist.adapters.UserWatchlistPagerAdapter;
import com.example.samanta.tvtrackermvp.ui.users.details.watchlist.fragments.UserWatchlistMovieFragment;
import com.example.samanta.tvtrackermvp.ui.users.details.watchlist.fragments.UserWatchlistTvShowFragment;
import com.example.samanta.tvtrackermvp.ui.watchlist.WatchlistActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserWatchlistActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, UserWatchlistView {

    @BindView(R.id.watchlistFragmentContainer)
    ViewPager watchlistFragmentContainer;
    @BindView(R.id.tabLayoutWatchlist)
    TabLayout tabLayoutWatchlist;
    @BindView(R.id.toolbarWatchlist)
    Toolbar toolbarWatchlist;
    @BindView(R.id.navViewWatchlist)
    NavigationView navViewWatchlist;
    @BindView(R.id.drawerLayoutWatchlist)
    DrawerLayout drawerLayoutWatchlist;

    UserWatchlistPresenter presenter = new UserWatchlistPresenterImpl();

    UserWatchlistPagerAdapter pagerAdapter;

    Intent intent;
    Bundle extras;
    String userID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watchlist);
        ButterKnife.bind(this);
        presenter.setBaseView(this);
        setUpFragments();

        intent = getIntent();
        extras = intent.getExtras();
        userID = extras.getString(Constants.USER_ID);
        presenter.getData(userID);
        setUpNavigationDrawer("username", "https://blakemedical.ca/wp-content/uploads/noimage.png");
    }

    @Override
    public void setUpFragments() {

        tabLayoutWatchlist.addTab(tabLayoutWatchlist.newTab().setText(R.string.movies));
        tabLayoutWatchlist.addTab(tabLayoutWatchlist.newTab().setText(R.string.tv_shows));

        pagerAdapter = new UserWatchlistPagerAdapter(getSupportFragmentManager(),
                tabLayoutWatchlist.getTabCount());
        List<Fragment> pages = new ArrayList<>();
        pages.add(new UserWatchlistMovieFragment());
        pages.add(new UserWatchlistTvShowFragment());
        pagerAdapter.setItems(pages);
        watchlistFragmentContainer.setAdapter(pagerAdapter);

        watchlistFragmentContainer.addOnPageChangeListener
                (new TabLayout.TabLayoutOnPageChangeListener(tabLayoutWatchlist));
        tabLayoutWatchlist.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                watchlistFragmentContainer.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                watchlistFragmentContainer.setCurrentItem(tab.getPosition());
            }
        });

    }

    @Override
    public void setUpNavigationDrawer(String username, String profilePicture) {

        setSupportActionBar(toolbarWatchlist);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle
                (this, drawerLayoutWatchlist, toolbarWatchlist,
                        R.string.navigation_drawer_open,
                        R.string.navigation_drawer_close);
        drawerLayoutWatchlist.addDrawerListener(toggle);
        toggle.syncState();

        navViewWatchlist.setNavigationItemSelectedListener(this);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);

        View header = navViewWatchlist.getHeaderView(0);
        HeaderView headerViewHolder = new HeaderView(header, username, profilePicture);
    }

    @Override
    public void onBackPressed() {

        if (drawerLayoutWatchlist.isDrawerOpen(GravityCompat.START)) {
            drawerLayoutWatchlist.closeDrawer(GravityCompat.START);
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
                startActivity(new Intent(this, WatchlistActivity.class));
                finish();
            case R.id.nav_myProfile:
                startActivity(new Intent(this, MyProfileActivity.class));
                break;
            case R.id.nav_users:
                startActivity(new Intent(this, UsersActivity.class));
                break;
        }
        drawerLayoutWatchlist.closeDrawer(GravityCompat.START);
        return true;
    }
}
