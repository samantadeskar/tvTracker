package com.example.samanta.tvtrackermvp.ui.movies.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.samanta.tvtrackermvp.R;
import com.example.samanta.tvtrackermvp.listeners.AddToWatchlistClickListener;
import com.example.samanta.tvtrackermvp.listeners.MovieClickListener;
import com.example.samanta.tvtrackermvp.pojo.Movie;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    private MovieClickListener movieClickListener;
    private final List<Movie> allMovies;
    private AddToWatchlistClickListener addToWatchlistClickListener;


    public MoviesAdapter() {
        allMovies = new ArrayList<>();
    }

    public MoviesAdapter(MovieClickListener listener,
                         AddToWatchlistClickListener addToWatchlistClickListener) {
        this.addToWatchlistClickListener = addToWatchlistClickListener;
        movieClickListener = listener;
        allMovies = new ArrayList<>();
    }


    public void setMovies(List<Movie> allMoviesList, List<Movie> watchedMoviesList) {
        if (allMoviesList != null) {
            this.allMovies.clear();
            this.allMovies.addAll(allMoviesList);
            if (watchedMoviesList != null) {
                for (Movie movie : allMovies) {
                    for (Movie watchedMovie : watchedMoviesList) {
                        if (movie.getId() == watchedMovie.getId()) {
                            movie.setWatched("true");
                        }
                    }

                }
            }
        }
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                              int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item, parent, false);
        return new MovieViewHolder(view, movieClickListener, addToWatchlistClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.bind(allMovies.get(position));

    }

    @Override
    public int getItemCount() {
        return allMovies.size();
    }

    public void addMovies(List<Movie> movies, List<Movie> watchedMoviesList) {
        this.allMovies.addAll(movies);
        if (watchedMoviesList != null) {
            for (Movie movie : allMovies) {
                for (Movie watchedMovie : watchedMoviesList) {
                    if (movie.getId() == watchedMovie.getId()) {
                        movie.setWatched("true");
                    }
                }

            }
        }
        notifyDataSetChanged();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imageViewMoviePoster)
        ImageView imageViewMoviePoster;
        @BindView(R.id.textViewMovieTitle)
        TextView textViewMovieTitle;
        @BindView(R.id.textViewMovieRating)
        TextView textViewMovieRating;
        @BindView(R.id.textViewMovieReleaseDate)
        TextView textViewMovieReleaseDate;
        @BindView(R.id.textViewWatchedMovie)
        TextView textViewWatchedMovie;
        @BindView(R.id.buttonAddToWatchlistMovie)
        Button buttonAddToWatchlistMovie;


        public MovieViewHolder(View itemView, MovieClickListener listener,
                               AddToWatchlistClickListener addToWatchlistClickListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick
        public void onMovieClick() {

            movieClickListener.onClick(allMovies.get(getAdapterPosition()));
        }

        @OnClick(R.id.buttonAddToWatchlistMovie)
        public void onButtonClick() {

            addToWatchlistClickListener.onClickButton(allMovies.get(getAdapterPosition()));
        }

        public void bind(Movie movie) {
            textViewMovieReleaseDate.setText(movie.getReleaseDate().split("-")[0]);
            textViewMovieTitle.setText(movie.getTitle());
            textViewMovieRating.setText(String.valueOf(movie.getRating()));
            Glide.with(itemView)
                    .load(movie.getPoster())
                    .apply(RequestOptions.placeholderOf(R.color.colorPrimary))
                    .into(imageViewMoviePoster);

            if (movie.getWatched().equals("true")) {
                textViewWatchedMovie.setVisibility(View.VISIBLE);
            } else {
                textViewWatchedMovie.setVisibility(View.INVISIBLE);
            }
        }
    }
}




