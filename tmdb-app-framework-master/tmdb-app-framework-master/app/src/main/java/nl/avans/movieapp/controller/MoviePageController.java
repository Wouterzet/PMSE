package nl.avans.movieapp.controller;

import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import nl.avans.movieapp.domain.Movie;
import nl.avans.movieapp.service.MovieAPI;
import nl.avans.movieapp.service.MovieApiResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviePageController extends BaseMovieAppController
        implements Callback<MovieApiResponse> {

    private final String LOG_TAG = this.getClass().getSimpleName();

    private MoviePageControllerListener listener;
    private final MovieAPI movieAPI;

    public MoviePageController(MoviePageControllerListener listener) {
        super();
        this.listener = listener;
        movieAPI = retrofit.create(MovieAPI.class);
    }

    public void loadLatestMovies(int pageNr) {
        Call<MovieApiResponse> call = movieAPI.loadLatestMovies(1);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<MovieApiResponse> call, Response<MovieApiResponse> response) {
        Log.d(LOG_TAG, "onResponse() - statuscode: " + response.code());

        if(response.isSuccessful()) {
            Log.d(LOG_TAG, "response: " + response.body());

            // Deserialization
            List<Movie> movies = response.body().getResults();
            listener.onMoviesAvailable(movies);
        } else {
            Log.e(LOG_TAG, "Not successful! Message: " + response.message());
        }
    }

    @Override
    public void onFailure(@NotNull Call<MovieApiResponse> call, Throwable t) {
        Log.e(LOG_TAG, "onFailure - " + t.getMessage());
        listener.onError(t.getMessage());
    }

    public interface MoviePageControllerListener {
        void onMoviesAvailable(List<Movie> movies);
        void onError(String message);
    }
}