package com.example.samanta.tvtrackermvp.ui.movies;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.example.samanta.tvtrackermvp.presenters.MoviesPresenter;
import com.example.samanta.tvtrackermvp.presenters.MoviesPresenterImpl;
import com.example.samanta.tvtrackermvp.ui.HeaderView;
import com.example.samanta.tvtrackermvp.ui.movies.adapters.MoviesPagerAdapter;
import com.example.samanta.tvtrackermvp.ui.movies.fragments.ComingSoonMoviesFragment;
import com.example.samanta.tvtrackermvp.ui.movies.fragments.NowPlayingMoviesFragment;
import com.example.samanta.tvtrackermvp.ui.movies.fragments.PopularMoviesFragment;
import com.example.samanta.tvtrackermvp.ui.movies.fragments.TopRatedMoviesFragment;
import com.example.samanta.tvtrackermvp.ui.myProfile.MyProfileActivity;
import com.example.samanta.tvtrackermvp.ui.search.SearchMoviesActivity;
import com.example.samanta.tvtrackermvp.ui.tvShows.TvShowsActivity;
import com.example.samanta.tvtrackermvp.ui.users.UsersActivity;
import com.example.samanta.tvtrackermvp.ui.watchlist.WatchlistActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MoviesView {

    @BindView(R.id.movieFragmentContainer)
    ViewPager movieFragmentContainer;
    @BindView(R.id.tabLayoutMovies)
    TabLayout tabLayoutMovies;
    @BindView(R.id.toolbarMovies)
    Toolbar toolbarMovies;
    @BindView(R.id.navViewMovies)
    NavigationView navViewMovies;
    @BindView(R.id.drawerLayoutMovies)
    DrawerLayout drawerLayoutMovies;

    MoviesPresenter presenter = new MoviesPresenterImpl();

    MoviesPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        ButterKnife.bind(this);
        presenter.setBaseView(this);
        setUpFragments();
        presenter.getData();
        setUpNavigationDrawer("username", "https://blakemedical.ca/wp-content/uploads/noimage.png");
    }


    public void setUpFragments() {
        tabLayoutMovies.addTab(tabLayoutMovies.newTab().setText(R.string.popular));
        tabLayoutMovies.addTab(tabLayoutMovies.newTab().setText(R.string.top_rated));
        tabLayoutMovies.addTab(tabLayoutMovies.newTab().setText(R.string.now_playing));
        tabLayoutMovies.addTab(tabLayoutMovies.newTab().setText(R.string.coming_soon));

        pagerAdapter = new MoviesPagerAdapter
                (getSupportFragmentManager(), tabLayoutMovies.getTabCount());
        List<Fragment> pages = new ArrayList<>();
        pages.add(new PopularMoviesFragment());
        pages.add(new TopRatedMoviesFragment());
        pages.add(new NowPlayingMoviesFragment());
        pages.add(new ComingSoonMoviesFragment());
        pagerAdapter.setItems(pages);
        movieFragmentContainer.setAdapter(pagerAdapter);

        movieFragmentContainer.addOnPageChangeListener
                (new TabLayout.TabLayoutOnPageChangeListener(tabLayoutMovies));
        tabLayoutMovies.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                movieFragmentContainer.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                movieFragmentContainer.setCurrentItem(tab.getPosition());
            }
        });

    }

    public void setUpNavigationDrawer(String username, String profilePicture) {

        setSupportActionBar(toolbarMovies);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle
                (this, drawerLayoutMovies, toolbarMovies,
                        R.string.navigation_drawer_open,
                        R.string.navigation_drawer_close);
        drawerLayoutMovies.addDrawerListener(toggle);
        toggle.syncState();

        navViewMovies.setNavigationItemSelectedListener(this);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);

        View header = navViewMovies.getHeaderView(0);
        HeaderView headerViewHolder = new HeaderView(header, username, profilePicture);

    }

    @Override
    public void onBackPressed() {
        if (drawerLayoutMovies.isDrawerOpen(GravityCompat.START)) {
            drawerLayoutMovies.closeDrawer(GravityCompat.START);
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
                break;
            case R.id.nav_users:
                startActivity(new Intent(this, UsersActivity.class));
                break;
        }
        drawerLayoutMovies.closeDrawer(GravityCompat.START);
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

        Intent intent = new Intent(this, SearchMoviesActivity.class);
        intent.putExtra(Constants.SEARCH_QUERY, query);
        startActivity(intent);

    }
}
