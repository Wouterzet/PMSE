package nl.avans.movieapp.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class  BaseMovieAppController {

    public static final String BASE_URL = "https://api.themoviedb.org/3/";
    public static final String BASE_POSTER_PATH_URL = "https://image.tmdb.org/t/p/w500/";
    public static final String API_KEY = "3cf9af08e7239646bf2cf208eb4fc7e8";
    public static final String SESSION_ID = "e03b6ecac0f39f8f398a627d8b38cfb5e6742e0c";

    protected final Retrofit retrofit;
    protected final Gson gson;

    public BaseMovieAppController() {
        gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

    }
}
