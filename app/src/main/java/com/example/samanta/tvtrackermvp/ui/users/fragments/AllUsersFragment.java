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
import com.example.samanta.tvtrackermvp.presenters.AllUsersPresenter;
import com.example.samanta.tvtrackermvp.presenters.AllUsersPresenterImpl;
import com.example.samanta.tvtrackermvp.ui.users.adapters.UsersAdapter;
import com.example.samanta.tvtrackermvp.ui.users.details.UserProfileActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AllUsersFragment extends Fragment
        implements AllUsersView, UserClickListener, FollowUserClickListener {

    private AllUsersPresenter presenter = new AllUsersPresenterImpl();
    UsersAdapter adapter;

    @BindView(R.id.recyclerAllUsers)
    RecyclerView recyclerViewAllUsers;

    public AllUsersFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_all_users,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);

        presenter.setBaseView(this);

        adapter = new UsersAdapter(this, this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerViewAllUsers.setLayoutManager(linearLayoutManager);
        recyclerViewAllUsers.setItemAnimator(new DefaultItemAnimator());
        recyclerViewAllUsers.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.getAllUsers();
    }

    @Override
    public void setUsers(List<User> userList, List<User> followedUserList) {
        adapter.setUsers(userList,followedUserList);
    }

    @Override
    public void toastFollowUser(String username) {
        Toast.makeText(getActivity(),"You just followed " + username,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void toastFollowedUser(String username) {
        Toast.makeText(getActivity(), username + " is already followed",
                Toast.LENGTH_SHORT).show();
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
    public void onButtonClick(User user){
        presenter.followUser(user);
    }
}
