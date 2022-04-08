package nl.avans.movieapp.controller;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import nl.avans.movieapp.domain.Movie;
import nl.avans.movieapp.domain.MovieList;
import nl.avans.movieapp.domain.SpecList;
import nl.avans.movieapp.service.MovieAPI;
import nl.avans.movieapp.service.MovieApiResponse;
import nl.avans.movieapp.service.MovieListSpecApiResponse;
import nl.avans.movieapp.service.MovieListsApiResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


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
        Call<MovieListSpecApiResponse> call = movieAPI.loadMovieListByID(7100269);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<MovieListSpecApiResponse> call, Response<MovieListSpecApiResponse> response) {
        Log.d(LOG_TAG, "onResponse() - statuscode: " + response.code());

        if(response.isSuccessful()) {
            Log.d(LOG_TAG, "response: Test " + response.message());
            Log.d(LOG_TAG, "response: Test " + response.raw());
            Log.d(LOG_TAG, "response: " + response.body());
            Log.d(LOG_TAG, "response: Test " + response.body().getResults());

            if(response.body().getResults() != null) {
                // Deserialization
                SpecList movieLists = response.body().getResults();
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
        void onMovieListsAvailable(SpecList movieLists);
    }
}
