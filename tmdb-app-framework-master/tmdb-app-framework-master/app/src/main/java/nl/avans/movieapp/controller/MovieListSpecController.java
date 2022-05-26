package nl.avans.movieapp.controller;

import android.util.Log;

import org.jetbrains.annotations.NotNull;

import nl.avans.movieapp.domain.MovieList;
import nl.avans.movieapp.service.MovieAPI;
import nl.avans.movieapp.service.MovieListSpecApiResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MovieListSpecController extends BaseMovieAppController implements Callback<MovieListSpecApiResponse> {

    private final String LOG_TAG = this.getClass().getSimpleName();
    public static final String BASE_URL = "https://api.themoviedb.org/3/";

    private MovieListsSpecControllerListener listener;


    private final MovieAPI movieAPI;
    private int id;

    public MovieListSpecController(MovieListsSpecControllerListener listener) {
        super();
        this.listener = listener;
        movieAPI = retrofit.create(MovieAPI.class);
    }

    public void loadMovieListByID(int id) {
        Call<MovieListSpecApiResponse> call = movieAPI.loadMovieListByID(id);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<MovieListSpecApiResponse> call, Response<MovieListSpecApiResponse> response) {
        Log.d(LOG_TAG, "onResponse() - statuscode: " + response.code());

        if(response.isSuccessful()) {
            Log.d(LOG_TAG, "response: Test " + response.message());
            Log.d(LOG_TAG, "response: Test " + response.raw());
            Log.d(LOG_TAG, "response: Test" + response.body());
            Log.d(LOG_TAG, "response: Test " + response.body().getResults());
            Log.d(LOG_TAG, "response: Test " + response.body());

            if(response.body().getResults() != null) {
                // Deserialization
                MovieList movieLists = response.body().getResults();
                Log.d("Laatse kans", movieLists.toString());
                listener.onMovieListsAvailable(movieLists);
            }
        } else {
            Log.e(LOG_TAG, "Not successful! Message: " + response.message());
        }
    }

    @Override
    public void onFailure(@NotNull Call<MovieListSpecApiResponse> call, Throwable t) {
        Log.e(LOG_TAG, "onFailure - " + t.getMessage());
    }

    /**
     *
     */
    public interface MovieListsSpecControllerListener {
        void onMovieListsAvailable(MovieList movieLists);
    }
}
