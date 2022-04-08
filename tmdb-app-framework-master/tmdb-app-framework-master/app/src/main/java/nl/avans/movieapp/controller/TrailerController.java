package nl.avans.movieapp.controller;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import nl.avans.movieapp.domain.Movie;
import nl.avans.movieapp.domain.MovieList;
import nl.avans.movieapp.domain.Trailer;
import nl.avans.movieapp.service.MovieAPI;
import nl.avans.movieapp.service.MovieListSpecApiResponse;
import nl.avans.movieapp.service.TrailerApiResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TrailerController implements Callback<TrailerApiResponse> {

    private final String LOG_TAG = this.getClass().getSimpleName();
    public static final String BASE_URL = "https://api.themoviedb.org/3/";

    private TrailerController.TrailerControllerListener listener;

    private final Retrofit retrofit;
    private final Gson gson;
    private final MovieAPI movieAPI;
    private int id;

    public TrailerController(TrailerController.TrailerControllerListener listener) {
        this.listener = listener;

        gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        movieAPI = retrofit.create(MovieAPI.class);
    }
    public void setId(int id){
        this.id=id;
    }

    public void loadTrailersForMovie(int id) {
        Call<TrailerApiResponse> call = movieAPI.loadTrailer(id);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<TrailerApiResponse> call, Response<TrailerApiResponse> response) {
        Log.d(LOG_TAG, "onResponse() - statuscode: " + response.code());

        if(response.isSuccessful()) {
            Log.d(LOG_TAG, "response: " + response.body());

            // Deserialization

            List<Trailer> trailers = response.body().getResults();
            Log.d("Laatse kans", trailers.toString());
            listener.onTrailersAvailable(trailers);
        } else {
            Log.e(LOG_TAG, "Not successful! Message: " + response.message());
        }
    }

    @Override
    public void onFailure(@NotNull Call<TrailerApiResponse> call, Throwable t) {
        Log.e(LOG_TAG, "onFailure - " + t.getMessage());
    }

    /**
     *
     */
    public interface TrailerControllerListener {
        void onTrailersAvailable(List<Trailer> trailers);
        void onError(String message);
    }
}