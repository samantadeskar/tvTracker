package com.example.samanta.tvtrackermvp.ui.users.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.samanta.tvtrackermvp.Constants.Constants;
import com.example.samanta.tvtrackermvp.R;
import com.example.samanta.tvtrackermvp.listeners.FollowUserClickListener;
import com.example.samanta.tvtrackermvp.listeners.UserClickListener;
import com.example.samanta.tvtrackermvp.pojo.User;
import com.example.samanta.tvtrackermvp.presenters.FollowedUsersPresenter;
import com.example.samanta.tvtrackermvp.presenters.FollowedUsersPresenterImpl;
import com.example.samanta.tvtrackermvp.ui.users.adapters.FollowedUsersAdapter;
import com.example.samanta.tvtrackermvp.ui.users.adapters.UsersAdapter;
import com.example.samanta.tvtrackermvp.ui.users.details.UserProfileActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FollowedUsersFragment extends Fragment
        implements FollowedUsersView, UserClickListener, FollowUserClickListener {

    private FollowedUsersPresenter presenter = new FollowedUsersPresenterImpl();
    FollowedUsersAdapter adapter;

    @BindView(R.id.recyclerFollowedUsers)
    RecyclerView recyclerViewFollowedUsers;

    public FollowedUsersFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_followed_users,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this,view);

        presenter.setBaseView(this);

        adapter = new FollowedUsersAdapter(this, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerViewFollowedUsers.setLayoutManager(linearLayoutManager);
        recyclerViewFollowedUsers.setItemAnimator(new DefaultItemAnimator());
        recyclerViewFollowedUsers.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.getFollowedUsers();
    }

    @Override
    public void setFollowedUsers(List<User> usersList) {
        adapter.setFollowedUsers(usersList);
    }

    @Override
    public void onClick(User user){
        Intent intent = new Intent(getActivity(), UserProfileActivity.class);
        Bundle extras = new Bundle();

        extras.putString(Constants.USER_ID, user.getUserID());
        intent.putExtras(extras);
        startActivity(intent);
    }

    @Override
    public void onButtonClick(User user) {
        presenter.unfollowUser(user);
    }

    @Override
    public void toastUnfollowed(String username) {
        Toast.makeText(getActivity(), username + " is unfollowed.",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void toastUnfollowError(String username) {
        Toast.makeText(getActivity(), username + " is not unfollowed. Please try again",
                Toast.LENGTH_SHORT).show();
    }
}
