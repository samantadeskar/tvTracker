package com.example.samanta.tvtrackermvp.ui.tvShows;

import android.app.SearchManager;
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
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.samanta.tvtrackermvp.Constants.Constants;
import com.example.samanta.tvtrackermvp.R;
import com.example.samanta.tvtrackermvp.presenters.TvShowsPresenter;
import com.example.samanta.tvtrackermvp.presenters.TvShowsPresenterImpl;
import com.example.samanta.tvtrackermvp.ui.HeaderView;
import com.example.samanta.tvtrackermvp.ui.movies.MoviesActivity;
import com.example.samanta.tvtrackermvp.ui.myProfile.MyProfileActivity;
import com.example.samanta.tvtrackermvp.ui.search.SearchTvShowsActivity;
import com.example.samanta.tvtrackermvp.ui.tvShows.adapters.TvShowsPagerAdapter;
import com.example.samanta.tvtrackermvp.ui.tvShows.fragments.OnTheAirTvShowsFragment;
import com.example.samanta.tvtrackermvp.ui.tvShows.fragments.PopularTvShowsFragment;
import com.example.samanta.tvtrackermvp.ui.tvShows.fragments.TopRatedTvShowsFragment;
import com.example.samanta.tvtrackermvp.ui.users.UsersActivity;
import com.example.samanta.tvtrackermvp.ui.watchlist.WatchlistActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TvShowsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, TvShowsView {

    @BindView(R.id.tvShowFragmentContainer)
    ViewPager tvShowFragmentContainer;
    @BindView(R.id.tabLayoutTvShows)
    TabLayout tabLayoutTvShows;
    @BindView(R.id.toolbarTvShows)
    Toolbar toolbarTvShows;
    @BindView(R.id.navViewTvShows)
    NavigationView navViewTvShows;
    @BindView(R.id.drawerLayoutTvShows)
    DrawerLayout drawerLayoutTvShows;

    TvShowsPagerAdapter pagerAdapter;

    TvShowsPresenter presenter = new TvShowsPresenterImpl();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_shows);
        ButterKnife.bind(this);

        setUpFragments();
        presenter.setBaseView(this);
        presenter.getUserInfo();
    }

    public void setUpFragments() {


        tabLayoutTvShows.addTab(tabLayoutTvShows.newTab().setText(R.string.popular));
        tabLayoutTvShows.addTab(tabLayoutTvShows.newTab().setText(R.string.top_rated));
        tabLayoutTvShows.addTab(tabLayoutTvShows.newTab().setText(R.string.on_the_air));

        pagerAdapter = new TvShowsPagerAdapter
                (getSupportFragmentManager(), tabLayoutTvShows.getTabCount());
        List<Fragment> pages = new ArrayList<>();
        pages.add(new PopularTvShowsFragment());
        pages.add(new TopRatedTvShowsFragment());
        pages.add(new OnTheAirTvShowsFragment());
        pagerAdapter.setItems(pages);
        tvShowFragmentContainer.setAdapter(pagerAdapter);

        tvShowFragmentContainer.addOnPageChangeListener
                (new TabLayout.TabLayoutOnPageChangeListener(tabLayoutTvShows));
        tabLayoutTvShows.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tvShowFragmentContainer.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                tvShowFragmentContainer.setCurrentItem(tab.getPosition());
            }
        });

    }

    public void setUpNavigationDrawer(String username, String profilePicture) {

        setSupportActionBar(toolbarTvShows);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle
                (this, drawerLayoutTvShows, toolbarTvShows,
                        R.string.navigation_drawer_open,
                        R.string.navigation_drawer_close);
        drawerLayoutTvShows.addDrawerListener(toggle);
        toggle.syncState();

        navViewTvShows.setNavigationItemSelectedListener(this);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);


        View header = navViewTvShows.getHeaderView(0);
        HeaderView headerViewHolder = new HeaderView(header, username, profilePicture);

    }


    @Override
    public void onBackPressed() {
        if (drawerLayoutTvShows.isDrawerOpen(GravityCompat.START)) {
            drawerLayoutTvShows.closeDrawer(GravityCompat.START);
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
                break;
            case R.id.nav_myProfile:
                startActivity(new Intent(this, MyProfileActivity.class));
            case R.id.nav_users:
                startActivity(new Intent(this, UsersActivity.class));
                break;
        }
        drawerLayoutTvShows.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.action_bar, menu);

        MenuItem search = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) search.getActionView();

        SearchManager searchManager =
                (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        searchView.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                getSearchInput(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });

        return true;
    }

    @Override
    public void getSearchInput(String query) {

        Intent intent = new Intent(this, SearchTvShowsActivity.class);
        intent.putExtra(Constants.SEARCH_QUERY, query);
        startActivity(intent);

    }

}
