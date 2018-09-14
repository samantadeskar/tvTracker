package com.example.samanta.tvtrackermvp.networking;

import com.example.samanta.tvtrackermvp.Constants.Constants;
import com.example.samanta.tvtrackermvp.interactors.MoviesInteractor;
import com.example.samanta.tvtrackermvp.interactors.MoviesInteractorImpl;
import com.example.samanta.tvtrackermvp.interactors.TvShowsInteractor;
import com.example.samanta.tvtrackermvp.interactors.TvShowsInteractorImpl;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BackendFactory {

    private static Retrofit retrofit = null;

    private static Retrofit getClient(String baseUrl) {

        if (retrofit == null) {
            Interceptor ceptor = new HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(ceptor)
                    .build();

            retrofit = new Retrofit.Builder()
                    .client(client)
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    private static ApiService getService() {
        return getClient(Constants.BASE_URL).create(ApiService.class);
    }

    public static MoviesInteractor getMoviesInteractor() {
        return new MoviesInteractorImpl(getService());
    }

    public static TvShowsInteractor getTvShowInteractor() {
        return new TvShowsInteractorImpl(getService());
    }

}
