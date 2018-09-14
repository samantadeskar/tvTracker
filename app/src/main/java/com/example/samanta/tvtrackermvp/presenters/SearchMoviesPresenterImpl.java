package com.example.samanta.tvtrackermvp.presenters;

import com.example.samanta.tvtrackermvp.firebase.authentication.AuthenticationHelper;
import com.example.samanta.tvtrackermvp.firebase.authentication.AuthenticationHelperImpl;
import com.example.samanta.tvtrackermvp.firebase.database.DatabaseHelper;
import com.example.samanta.tvtrackermvp.firebase.database.DatabaseHelperImpl;
import com.example.samanta.tvtrackermvp.firebase.database.WatchedMoviesCallback;
import com.example.samanta.tvtrackermvp.firebase.database.WatchlistMoviesCallback;
import com.example.samanta.tvtrackermvp.interactors.MoviesInteractor;
import com.example.samanta.tvtrackermvp.networking.BackendFactory;
import com.example.samanta.tvtrackermvp.pojo.Movie;
import com.example.samanta.tvtrackermvp.response.MovieResponse;
import com.example.samanta.tvtrackermvp.ui.search.SearchMoviesView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchMoviesPresenterImpl implements SearchMoviesPresenter{

    private SearchMoviesView view;
    private MoviesInteractor interactor = BackendFactory.getMoviesInteractor();
    private AuthenticationHelper authenticationHelper = new AuthenticationHelperImpl();
    private DatabaseHelper databaseHelper = new DatabaseHelperImpl();


    @Override
    public void setBaseView(SearchMoviesView view) {
        this.view = view;
    }

    @Override
    public void addSearchedMovies(String query, int curentPage) {
        interactor.getSearchedMoves(curentPage,addMovieResponseCallback(),query);
    }

    @Override
    public void getSearchedMovies(String query, int i) {

        interactor.getSearchedMoves(1,getMovieResponseCallback(), query);
    }

    @Override
    public void saveMovieOnWatchlist(final Movie movie) {

        String userID = authenticationHelper.getCurrentUserID();
        databaseHelper.addMovieToWatchlist(new WatchlistMoviesCallback() {
            @Override
            public void onCallbackWatchlistMovies(boolean isOnWatchlist) {
                if(isOnWatchlist){
                    view.toastOnWatchlist(movie.getTitle());
                }
                else {
                    view.toastAddedToWatchlist(movie.getTitle());
                }
            }
        },movie,userID);

    }

    @Override
    public void getWatchedMovies(final List<Movie> allMovieList, final String callback) {
        String userID = authenticationHelper.getCurrentUserID();
        databaseHelper.getWatchedMovies(new WatchedMoviesCallback() {
            @Override
            public void onWatchedMoviesCallback(List<Movie> movieList) {
                if(callback.equals("getMovies")){
                    view.setData(allMovieList,movieList);
                }else if(callback.equals("addMovies")){
                    view.addData(allMovieList,movieList);
                }
            }
        },userID);
    }

    private Callback<MovieResponse> getMovieResponseCallback() {

        return new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if(response.isSuccessful()){
                    getWatchedMovies(response.body().getMovieList(),"getMovies");
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        };

    }

    private Callback<MovieResponse> addMovieResponseCallback() {

        return new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if(response.isSuccessful()){
                    getWatchedMovies(response.body().getMovieList(),"addMovies");
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        };

    }
}
