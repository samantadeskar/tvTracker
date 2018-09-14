package com.example.samanta.tvtrackermvp.ui.users.details.watchlist.adapters;

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
import com.example.samanta.tvtrackermvp.listeners.WatchlistTvShowClickListener;
import com.example.samanta.tvtrackermvp.pojo.WatchlistTvShows;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserWatchlistTvShowsAdapter extends RecyclerView.Adapter<UserWatchlistTvShowsAdapter.UserWatchlistTvShowViewHolder> {

    private WatchlistTvShowClickListener tvShowClickListener;
    private final List<WatchlistTvShows> allWatchlistTvShows;

    public UserWatchlistTvShowsAdapter() {
        allWatchlistTvShows = new ArrayList<>();
    }

    public UserWatchlistTvShowsAdapter(WatchlistTvShowClickListener listener) {
        this.tvShowClickListener = listener;
        allWatchlistTvShows = new ArrayList<>();
    }

    public void setTvShows(List<WatchlistTvShows> watchlistTvShows) {

        if (watchlistTvShows != null) {
            this.allWatchlistTvShows.clear();
            this.allWatchlistTvShows.addAll(watchlistTvShows);
        }
        notifyDataSetChanged();

    }


    @NonNull
    @Override
    public UserWatchlistTvShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.watchlist_item, parent, false);
        return new UserWatchlistTvShowViewHolder(view, tvShowClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull UserWatchlistTvShowViewHolder holder, int position) {
        holder.bind(allWatchlistTvShows.get(position));
    }

    @Override
    public int getItemCount() {
        return allWatchlistTvShows.size();
    }

    public class UserWatchlistTvShowViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.imageViewWatchlistMoviePoster)
        ImageView imageViewWatchlistMoviePoster;
        @BindView(R.id.textViewWatchlistMovieTitle)
        TextView textViewWatchlistMovieTitle;
        @BindView(R.id.textViewWatchlistMovieRating)
        TextView textViewWatchlistMovieRating;
        @BindView(R.id.textViewWatchlistMovieReleaseDate)
        TextView textViewWatchlistMovieReleaseDate;
        @BindView(R.id.textViewWatchlistWatchedMovie)
        TextView textViewWatchedWatchlistMovie;

        public UserWatchlistTvShowViewHolder(View itemView, WatchlistTvShowClickListener listener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick
        public void onTvShow() {
            tvShowClickListener.onClick(allWatchlistTvShows.get(getAdapterPosition()));
        }

        public void bind(WatchlistTvShows watchlistTvShows) {
            textViewWatchlistMovieReleaseDate.setText(watchlistTvShows.getFirstAirDate().split("-")[0]);
            textViewWatchlistMovieTitle.setText(watchlistTvShows.getTitle());
            textViewWatchlistMovieRating.setText(String.valueOf(watchlistTvShows.getRating()));

            Glide.with(itemView)
                    .load(watchlistTvShows.getPoster())
                    .apply(RequestOptions.placeholderOf(R.color.colorPrimary))
                    .into(imageViewWatchlistMoviePoster);

            textViewWatchedWatchlistMovie.setVisibility(View.INVISIBLE);

        }
    }

}
