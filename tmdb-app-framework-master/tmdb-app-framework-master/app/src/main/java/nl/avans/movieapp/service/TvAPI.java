package nl.avans.movieapp.service;

import android.util.Log;

import nl.avans.movieapp.domain.MovieList;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

import static nl.avans.movieapp.controller.BaseMovieAppController.API_KEY;
import static nl.avans.movieapp.controller.BaseMovieAppController.SESSION_ID;

public interface TvAPI {

    @GET("tv/popular?api_key=" + API_KEY)
    Call<TvApiResponse> loadTrendingTv();

    @GET("tv/{tv_id}?api_key=" + API_KEY)
    Call<TvApiResponse> loadTvCommentsById(@Path("tv_id") int id);

    @GET("account/{account_id}/lists?api_key=" + API_KEY + "&language=en-US&session_id=" + SESSION_ID)
    Call<MovieListsApiResponse> loadMovieListsForUser();

    @POST("list?api_key=" + API_KEY + "&language=en-US&session_id=" + SESSION_ID)
    Call<CreateMovieListApiResponse> createMovieList(@Body MovieList movieList);
}
