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
import nl.avans.movieapp.service.TvDetailApiResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 */
public class TvSpecController
        extends BaseMovieAppController
        implements Callback<TvDetailApiResponse> {

    private final String LOG_TAG = this.getClass().getSimpleName();

    private TvSpecControllerListener listener;
    private final TvAPI movieAPI;

    public TvSpecController(TvSpecController.TvSpecControllerListener listener) {
        super();
        this.listener = listener;
        movieAPI = retrofit.create(TvAPI.class);
    }


    public void loadTvById(int movie_id){
        Call<TvDetailApiResponse> call = movieAPI.loadTvById(1);
        Log.d("Controller",String.valueOf(1));
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<TvDetailApiResponse> call, Response<TvDetailApiResponse> response) {
        Log.d(LOG_TAG, "onResponse() - statuscode: " + response.code());

        if(response.isSuccessful()) {
            Log.d(LOG_TAG, "response: " + response.body());
//Hier gaat het fout krijg 0 response
            // Deserialization
            Tv tv = response.body().getResults();
            listener.onMoviesAvailable(tv);
        } else {
            Log.e(LOG_TAG, "Not successful! Message: " + response.message());
        }
    }

    @Override
    public void onFailure(@NotNull Call<TvDetailApiResponse> call, Throwable t) {
        Log.e(LOG_TAG, "onFailure - " + t.getMessage());
        listener.onError(t.getMessage());
    }

    public interface TvSpecControllerListener {
        void onMoviesAvailable(Tv tv);
        void onError(String message);
    }
}
