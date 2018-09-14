package com.example.samanta.tvtrackermvp.ui.watchlist.adapters;

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
import com.example.samanta.tvtrackermvp.listeners.WatchlistMovieClickListener;
import com.example.samanta.tvtrackermvp.pojo.WatchlistMovies;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WatchlistMoviesAdapter extends RecyclerView.Adapter<WatchlistMoviesAdapter.WatchlistMovieViewHolder>{

    private WatchlistMovieClickListener movieClickListener;
    private final List<WatchlistMovies> allWatchlistMovies;

    public WatchlistMoviesAdapter(){
        allWatchlistMovies = new ArrayList<>();
    }

    public WatchlistMoviesAdapter(WatchlistMovieClickListener listener){
        this.movieClickListener = listener;
        allWatchlistMovies = new ArrayList<>();
    }

    public void setMovies(List<WatchlistMovies> allWatchlistMoviesList){

        if (allWatchlistMoviesList != null){
            this.allWatchlistMovies.clear();
            this.allWatchlistMovies.addAll(allWatchlistMoviesList);
        }
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public WatchlistMovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.watchlist_item,parent,false);
        return new WatchlistMovieViewHolder(view,movieClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull WatchlistMovieViewHolder holder, int position) {

        holder.bind(allWatchlistMovies.get(position));
    }

    @Override
    public int getItemCount() {
        return allWatchlistMovies.size();
    }

    class WatchlistMovieViewHolder extends RecyclerView.ViewHolder{

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

        public WatchlistMovieViewHolder(View itemView, WatchlistMovieClickListener listener) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        @OnClick
        public void onMovieClick(){
            movieClickListener.onClick(allWatchlistMovies.get(getAdapterPosition()));
        }

        public void bind(WatchlistMovies movie){
            textViewWatchlistMovieReleaseDate.setText(movie.getReleaseDate().split("-")[0]);
            textViewWatchlistMovieTitle.setText(movie.getTitle());
            textViewWatchlistMovieRating.setText(String.valueOf(movie.getRating()));

            Glide.with(itemView)
                    .load(movie.getPoster())
                    .apply(RequestOptions.placeholderOf(R.color.colorPrimary))
                    .into(imageViewWatchlistMoviePoster);

            if (movie.getWatched().equals("true")) {
                textViewWatchedWatchlistMovie.setVisibility(View.VISIBLE);
            } else {
                textViewWatchedWatchlistMovie.setVisibility(View.INVISIBLE);
            }
        }
    }

}
