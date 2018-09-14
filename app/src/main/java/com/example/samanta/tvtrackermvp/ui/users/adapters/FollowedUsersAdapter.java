package com.example.samanta.tvtrackermvp.ui.users.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.samanta.tvtrackermvp.R;
import com.example.samanta.tvtrackermvp.listeners.FollowUserClickListener;
import com.example.samanta.tvtrackermvp.listeners.UserClickListener;
import com.example.samanta.tvtrackermvp.pojo.User;
import com.example.samanta.tvtrackermvp.ui.users.fragments.FollowedUsersFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FollowedUsersAdapter extends RecyclerView.Adapter<FollowedUsersAdapter.FollowedUsersViewHolder> {

    private UserClickListener userClickListener;
    private List<User> followedUsersList;
    private FollowUserClickListener followUserClickListener;


    public FollowedUsersAdapter(UserClickListener userClickListener, FollowUserClickListener clickListener) {

        this.userClickListener = userClickListener;
        followedUsersList = new ArrayList<>();
        this.followUserClickListener = clickListener;
    }

    public void setFollowedUsers(List<User> usersList) {

        if(usersList!=null){
            this.followedUsersList.clear();
            this.followedUsersList.addAll(usersList);
        }
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public FollowedUsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_item,parent,false);
        return new FollowedUsersViewHolder(view,userClickListener, followUserClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull FollowedUsersViewHolder holder, int position) {

        holder.bind(followedUsersList.get(position));

    }
    @Override
    public int getItemCount() {
        return followedUsersList.size();
    }

    public class FollowedUsersViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imageViewUserPicture)
        ImageView imageViewUserPicture;
        @BindView(R.id.textViewUserUsername)
        TextView textViewUserUsername;
        @BindView(R.id.textViewUserStatus)
        TextView textViewUserStatus;
        @BindView(R.id.imageButtonFollowUser)
        ImageButton imageButtonFollowUser;
        @BindView(R.id.textViewFollowedUser)
        TextView textViewFollowedUser;
        @BindView(R.id.buttonUnfollowUser)
        Button buttonUnfollowUser;


        public FollowedUsersViewHolder(View itemView,
                                       UserClickListener userClickListener,
                                       FollowUserClickListener clickListener) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        @OnClick
        public void onUserClick(){
            userClickListener.onClick(followedUsersList.get(getAdapterPosition()));
        }

        @OnClick(R.id.buttonUnfollowUser)
        public void onButtonClick(){
            followUserClickListener.onButtonClick(followedUsersList.get(getAdapterPosition()));
        }

        public void bind(User user){
            textViewUserUsername.setText(user.getUsername());
            textViewUserStatus.setText(user.getStatus());
            Glide.with(itemView)
                    .load(user.getImage())
                    .apply(RequestOptions.placeholderOf(R.drawable.noimage))
                    .into(imageViewUserPicture);

                textViewFollowedUser.setVisibility(View.INVISIBLE);
                imageButtonFollowUser.setVisibility(View.INVISIBLE);
                buttonUnfollowUser.setVisibility(View.VISIBLE);
        }
    }
}
