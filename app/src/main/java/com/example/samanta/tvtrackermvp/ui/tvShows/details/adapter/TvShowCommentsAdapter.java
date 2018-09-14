package com.example.samanta.tvtrackermvp.ui.tvShows.details.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.samanta.tvtrackermvp.R;
import com.example.samanta.tvtrackermvp.pojo.TvShowComments;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TvShowCommentsAdapter extends
        RecyclerView.Adapter<TvShowCommentsAdapter.TvShowCommentsViewHolder> {

    private final List<TvShowComments> tvShowCommentsList;

    public TvShowCommentsAdapter() {
        tvShowCommentsList = new ArrayList<>();
    }

    public TvShowCommentsAdapter(List<TvShowComments> commentsList) {
        this.tvShowCommentsList = commentsList;
    }

    public void setTvShowCommentsList(List<TvShowComments> commentsList) {

        if (commentsList != null) {
            this.tvShowCommentsList.clear();
            this.tvShowCommentsList.addAll(commentsList);
            notifyDataSetChanged();
        }

    }


    @NonNull
    @Override
    public TvShowCommentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comment_tv_show_item, parent, false);
        return new TvShowCommentsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvShowCommentsViewHolder holder, int position) {
        holder.bind(tvShowCommentsList.get(position));
    }

    @Override
    public int getItemCount() {
        return tvShowCommentsList.size();
    }

    public class TvShowCommentsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imageViewUserTvShowCommentProfilePicture)
        ImageView imageViewUserProfilePicture;
        @BindView(R.id.textViewTvShowCommentUsername)
        TextView textViewCommentUsername;
        @BindView(R.id.textViewTvShowComment)
        TextView textViewComment;


        public TvShowCommentsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(TvShowComments tvShowComments) {

            textViewCommentUsername.setText(tvShowComments.getUserName());
            textViewComment.setText(tvShowComments.getComment());

            Glide.with(itemView)
                    .load(tvShowComments.getProfilePicture())
                    .apply(RequestOptions.placeholderOf(R.drawable.noimage))
                    .into(imageViewUserProfilePicture);

        }
    }
}
