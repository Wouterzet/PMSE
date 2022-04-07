package nl.avans.movieapp.controller;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import nl.avans.movieapp.domain.Movie;
import nl.avans.movieapp.service.CommentApiResponse;
import nl.avans.movieapp.service.MovieApiResponse;
import nl.avans.movieapp.service.MovieAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieController
        extends BaseMovieAppController
        implements Callback<MovieApiResponse> {

    private final String LOG_TAG = this.getClass().getSimpleName();

    private MovieControllerListener listener;
    private final MovieAPI movieAPI;

    public MovieController(MovieControllerListener listener) {
        super();
        this.listener = listener;
        movieAPI = retrofit.create(MovieAPI.class);
    }

    public void loadTrendingMoviesPerWeek(int pageNr) {
        Call<MovieApiResponse> call = movieAPI.loadTrendingMoviesByWeek(pageNr);
        call.enqueue(this);
    }
    public void loadMovieById(int id){
        Call<MovieApiResponse> call = movieAPI.loadMovieById(id);
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

    public interface MovieControllerListener {
        void onMoviesAvailable(List<Movie> movies);
        void onError(String message);
    }
}
