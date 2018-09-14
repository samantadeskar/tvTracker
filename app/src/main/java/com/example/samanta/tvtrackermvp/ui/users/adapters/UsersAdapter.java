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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UsersViewHolder>{

    private UserClickListener userClickListener;
    private FollowUserClickListener followUserClickListener;
    private List<User> allUsers;

    public UsersAdapter() {
    }

    public UsersAdapter(UserClickListener userClickListener, FollowUserClickListener followUserClickListener) {
        this.userClickListener = userClickListener;
        this.followUserClickListener = followUserClickListener;
        allUsers = new ArrayList<>();
    }

    public void setUsers(List<User> userList, List<User> followedUserList) {

        if(userList!=null){
            this.allUsers.clear();
            this.allUsers.addAll(userList);
            if(followedUserList!=null){
                for(User user : allUsers){
                    for(User followedUser: followedUserList){
                        if(user.getUserID() == followedUser.getUserID()){
                            user.setFollowed("true");
                        }
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_item,parent,false);
        return new UsersViewHolder(view,userClickListener,followUserClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersViewHolder holder, int position) {
        holder.bind(allUsers.get(position));
    }

    @Override
    public int getItemCount() {
        return allUsers.size();
    }

    public class UsersViewHolder extends RecyclerView.ViewHolder {

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



        public UsersViewHolder(View itemView,
                               UserClickListener userClickListener,
                 FollowUserClickListener followUserClickListener) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        @OnClick
        public void onUserClick(){
            userClickListener.onClick(allUsers.get(getAdapterPosition()));
        }

        @OnClick(R.id.imageButtonFollowUser)
        public void onButtonClick(){
            followUserClickListener.onButtonClick(allUsers.get(getAdapterPosition()));
        }

        public void bind(User user){
            textViewUserUsername.setText(user.getUsername());
            textViewUserStatus.setText(user.getStatus());
            Glide.with(itemView)
                    .load(user.getImage())
                    .apply(RequestOptions.placeholderOf(R.drawable.noimage))
                    .into(imageViewUserPicture);

            if(user.getFollowed().equals("true")){
                textViewFollowedUser.setVisibility(View.VISIBLE);
                imageButtonFollowUser.setVisibility(View.INVISIBLE);
            }
            else {
                textViewFollowedUser.setVisibility(View.INVISIBLE);
                imageButtonFollowUser.setVisibility(View.VISIBLE);
                buttonUnfollowUser.setVisibility(View.INVISIBLE);
            }

        }
    }
}
