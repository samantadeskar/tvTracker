package com.example.samanta.tvtrackermvp.networking;

import com.example.samanta.tvtrackermvp.pojo.Movie;
import com.example.samanta.tvtrackermvp.response.MovieResponse;
import com.example.samanta.tvtrackermvp.response.TvShowEpisodeResponse;
import com.example.samanta.tvtrackermvp.response.TvShowResponse;
import com.example.samanta.tvtrackermvp.response.TvShowSeasonResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    //get popular movies
    @GET("movie/popular/")
    Call<MovieResponse> getPopularMovies(@Query("page") int page,
                                         @Query("api_key") String api_key);

    //get now playing movies
    @GET("movie/now_playing/")
    Call<MovieResponse> getNowPlayingMovies(@Query("page") int page,
                                            @Query("api_key") String api_key);

    //get top rated movies
    @GET("movie/top_rated/")
    Call<MovieResponse> getTopRatedMovies(@Query("page") int page,
                                          @Query("api_key") String api_key);

    //get upcoming movies
    @GET("movie/upcoming/")
    Call<MovieResponse> getCommingSoonMovies(@Query("page") int page,
                                             @Query("api_key") String api_key);

    //get popular tv shows
    @GET("tv/popular/")
    Call<TvShowResponse> getPopularTvShow(@Query("page") int page,
                                          @Query("api_key") String api_key);

    //get top rated tv shows
    @GET("tv/top_rated/")
    Call<TvShowResponse> getTopRatedTvShow(@Query("page") int page,
                                           @Query("api_key") String api_key);

    //get on the air tv shows
    @GET("tv/on_the_air/")
    Call<TvShowResponse> getOnTheAirTvShow(@Query("page") int page,
                                           @Query("api_key") String api_key);

    //search movies
    @GET("search/movie/")
    Call<MovieResponse> getSearchedMovie(@Query("page") int page,
                                         @Query("api_key") String api_key,
                                         @Query("query") String query);

    //search tv show
    @GET("search/tv")
    Call<TvShowResponse> getSearchedTvShow(@Query("page") int page,
                                           @Query("api_key") String api_key,
                                           @Query("query") String query);

    //get seasons
    @GET("tv/{tv_id}")
    Call<TvShowSeasonResponse> getTvShowSeasons(@Path("tv_id") int tv_id,
                                                @Query("api_key") String api_key);

    @GET("tv/{tv_id}/season/{season_number}")
    Call<TvShowEpisodeResponse> getTvShowEpisodes(@Path("tv_id") int tv_id,
                                                  @Path("season_number") int season_number,
                                                  @Query("api_key") String api_key);

    @GET("movie/{movie_id}")
    Call<Movie> getMovieDetails(@Path("movie_id") int movie_id,
                                @Query("api_key") String api_key);


}