package com.example.samanta.tvtrackermvp.ui.users;

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
import com.example.samanta.tvtrackermvp.presenters.UsersPresenter;
import com.example.samanta.tvtrackermvp.presenters.UsersPresenterImpl;
import com.example.samanta.tvtrackermvp.ui.HeaderView;
import com.example.samanta.tvtrackermvp.ui.movies.MoviesActivity;
import com.example.samanta.tvtrackermvp.ui.myProfile.MyProfileActivity;
import com.example.samanta.tvtrackermvp.ui.tvShows.TvShowsActivity;
import com.example.samanta.tvtrackermvp.ui.users.adapters.UsersPagerAdapter;
import com.example.samanta.tvtrackermvp.ui.users.fragments.AllUsersFragment;
import com.example.samanta.tvtrackermvp.ui.users.fragments.FollowedUsersFragment;
import com.example.samanta.tvtrackermvp.ui.watchlist.WatchlistActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UsersActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,UsersView{

    @BindView(R.id.usersFragmentContainer)
    ViewPager usersFragmentContainer;
    @BindView(R.id.tabLayoutUsers)
    TabLayout tabLayoutUsers;
    @BindView(R.id.toolbarUsers)
    Toolbar toolbarUsers;
    @BindView(R.id.navViewUsers)
    NavigationView navViewUsers;
    @BindView(R.id.drawerLayoutUsers)
    DrawerLayout drawerLayoutUsers;

    UsersPresenter presenter = new UsersPresenterImpl();

    UsersPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        ButterKnife.bind(this);
        presenter.setBaseView(this);
        setUpFragments();
        presenter.getData();
    }

    @Override
    public void setUpFragments() {

        tabLayoutUsers.addTab(tabLayoutUsers.newTab().setText(R.string.all_users));
        tabLayoutUsers.addTab(tabLayoutUsers.newTab().setText(R.string.followed_users));

        pagerAdapter = new UsersPagerAdapter(getSupportFragmentManager(), tabLayoutUsers.getTabCount());

        List<Fragment> pages = new ArrayList<>();
        pages.add(new AllUsersFragment());
        pages.add(new FollowedUsersFragment());
        pagerAdapter.setItems(pages);
        usersFragmentContainer.setAdapter(pagerAdapter);

        usersFragmentContainer.addOnPageChangeListener
                (new TabLayout.TabLayoutOnPageChangeListener(tabLayoutUsers));
        tabLayoutUsers.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                usersFragmentContainer.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                usersFragmentContainer.setCurrentItem(tab.getPosition());
            }
        });

    }

    @Override
    public void setUpNavigationDrawer(String username, String profilePicture) {

        setSupportActionBar(toolbarUsers);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle
                (this, drawerLayoutUsers,toolbarUsers,
                        R.string.navigation_drawer_open,
                        R.string.navigation_drawer_close);
        drawerLayoutUsers.addDrawerListener(toggle);
        toggle.syncState();

        navViewUsers.setNavigationItemSelectedListener(this);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);

        View header = navViewUsers.getHeaderView(0);
        HeaderView headerViewHolder = new HeaderView(header,username,profilePicture);

    }

    @Override
    public void onBackPressed() {
        if(drawerLayoutUsers.isDrawerOpen(GravityCompat.START)){
            drawerLayoutUsers.closeDrawer(GravityCompat.START);
        }
        else {
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
        }
        drawerLayoutUsers.closeDrawer(GravityCompat.START);
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

      //  Intent intent = new Intent(this, SearchUsersActivity.class);
        //intent.putExtra(Constants.SEARCH_QUERY, query);
        //startActivity(intent);

    }
}
