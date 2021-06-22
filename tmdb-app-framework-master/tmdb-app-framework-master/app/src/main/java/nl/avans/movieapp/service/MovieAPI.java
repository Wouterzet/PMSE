package nl.avans.movieapp.service;

import nl.avans.movieapp.domain.MovieList;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

import static nl.avans.movieapp.controller.BaseMovieAppController.API_KEY;
import static nl.avans.movieapp.controller.BaseMovieAppController.SESSION_ID;

public interface MovieAPI {

    @GET("trending/movie/week?api_key=" + API_KEY)
    Call<MovieApiResponse> loadTrendingMoviesByWeek(@Query("page") int pageNr);

    @GET("account/{account_id}/lists?api_key=" + API_KEY + "&language=en-US&session_id=" + SESSION_ID)
    Call<MovieListsApiResponse> loadMovieListsForUser();

    @POST("list?api_key=" + API_KEY + "&language=en-US&session_id=" + SESSION_ID)
    Call<CreateMovieListApiResponse> createMovieList(@Body MovieList movieList);
}
