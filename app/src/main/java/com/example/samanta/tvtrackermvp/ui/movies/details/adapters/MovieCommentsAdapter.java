package com.example.samanta.tvtrackermvp.ui.movies.details.adapters;

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
import com.example.samanta.tvtrackermvp.pojo.MovieComments;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieCommentsAdapter extends
        RecyclerView.Adapter<MovieCommentsAdapter.MovieCommentsViewHolder> {


    private final List<MovieComments> movieCommentsList;

    public MovieCommentsAdapter() {
        movieCommentsList = new ArrayList<>();
    }

    public MovieCommentsAdapter(List<MovieComments> commentsList) {
        this.movieCommentsList = commentsList;
    }

    public void setMovieCommentsList(List<MovieComments> commentsList) {
        if (commentsList != null) {
            this.movieCommentsList.clear();
            this.movieCommentsList.addAll(commentsList);
            notifyDataSetChanged();
        }
    }


    @NonNull
    @Override
    public MovieCommentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comment_item, parent, false);
        return new MovieCommentsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieCommentsViewHolder holder, int position) {
        holder.bind(movieCommentsList.get(position));
    }

    @Override
    public int getItemCount() {
        return movieCommentsList.size();
    }


    public class MovieCommentsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imageViewUserProfilePicture)
        ImageView imageViewUserProfilePicture;
        @BindView(R.id.textViewCommentUsername)
        TextView textViewMovieCommentUsername;
        @BindView(R.id.textViewComment)
        TextView textViewMovieComment;


        public MovieCommentsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(MovieComments movieComments) {

            textViewMovieCommentUsername.setText(movieComments.getUserName());
            textViewMovieComment.setText(movieComments.getComment());

            Glide.with(itemView)
                    .load(movieComments.getProfilePicture())
                    .apply(RequestOptions.placeholderOf(R.drawable.noimage))
                    .into(imageViewUserProfilePicture);
        }
    }
}
