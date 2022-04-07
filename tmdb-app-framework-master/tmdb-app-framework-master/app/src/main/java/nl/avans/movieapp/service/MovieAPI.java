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

public interface MovieAPI {

    @GET("trending/movie/week?api_key=" + API_KEY)
    Call<MovieApiResponse> loadTrendingMoviesByWeek(@Query("page") int pageNr);

    @GET("movie/now_playing?api_key=" + API_KEY + "&language=en-US")
    Call<MovieApiResponse> loadLatestMovies(@Query("page") int pageNr);

    @GET("movie/{movie_id}/?api_key=" + API_KEY)
    Call<MovieApiResponse> loadMovieById(@Path("movie_id") int id);

    @GET("movie/{movie_id}/reviews?api_key=" + API_KEY)
    Call<CommentApiResponse> loadMovieCommentsById(@Path("movie_id") int id);

    @GET("account/{account_id}/lists?api_key=" + API_KEY + "&language=en-US&session_id=" + SESSION_ID)
    Call<MovieListsApiResponse> loadMovieListsForUser();

    @GET("lists/{list_id}?api_key=" + API_KEY + "&language=en-US&session_id=" + SESSION_ID)
    Call<MovieListSpecApiResponse> loadMovieListByID(@Path("list_id") int id);

    @POST("list?api_key=" + API_KEY + "&language=en-US&session_id=" + SESSION_ID)
    Call<CreateMovieListApiResponse> createMovieList(@Body MovieList movieList);

    @POST("list/{list_id}/add_item?api_key=" + API_KEY + "&language=en-US&session_id=" + SESSION_ID)
    Call<CreateMovieListApiResponse> addMovieToList(@Body int movieId, @Path("list_id")int listId);

    @GET("movie/{movie_id}/videos?api_key=" + API_KEY + "&language=en-US&session_id=" + SESSION_ID)
    Call<TrailerApiResponse> loadTrailer(@Path("movie_id")int movieId);
}
