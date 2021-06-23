package nl.avans.movieapp.controller;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jetbrains.annotations.NotNull;
import java.util.List;

import nl.avans.movieapp.domain.Comment;
import nl.avans.movieapp.domain.Movie;
import nl.avans.movieapp.domain.Tv;

import nl.avans.movieapp.service.CommentApiResponse;
import nl.avans.movieapp.service.MovieApiResponse;
import nl.avans.movieapp.service.MovieAPI;
import nl.avans.movieapp.service.TvAPI;
import nl.avans.movieapp.service.TvApiResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 */
public class TvController
        extends BaseMovieAppController
        implements Callback<TvApiResponse> {

    private final String LOG_TAG = this.getClass().getSimpleName();

    private TvControllerListener listener;
    private final TvAPI tvAPI;

    public TvController(TvController.TvControllerListener listener) {
        super();
        this.listener = listener;
        tvAPI = retrofit.create(TvAPI.class);
    }


    public void loadTvShows(){
        Call<TvApiResponse> call = tvAPI.loadTrendingTv();
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<TvApiResponse> call, Response<TvApiResponse> response) {
        Log.d(LOG_TAG, "onResponse() - statuscode: " + response.code());

        if(response.isSuccessful()) {
            Log.d(LOG_TAG, "response: " + response.body());

            // Deserialization
            List<Tv> movies = response.body().getResults();
            listener.onMoviesAvailable(movies);
        } else {
            Log.e(LOG_TAG, "Not successful! Message: " + response.message());
        }
    }

    @Override
    public void onFailure(@NotNull Call<TvApiResponse> call, Throwable t) {
        Log.e(LOG_TAG, "onFailure - " + t.getMessage());
        listener.onError(t.getMessage());
    }

    public interface TvControllerListener {
        void onMoviesAvailable(List<Tv> movies);
        void onError(String message);
    }
}
