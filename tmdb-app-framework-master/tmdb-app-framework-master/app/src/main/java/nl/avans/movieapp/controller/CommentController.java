package nl.avans.movieapp.controller;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jetbrains.annotations.NotNull;
import java.util.List;

import nl.avans.movieapp.domain.Comment;
import nl.avans.movieapp.domain.Movie;
import nl.avans.movieapp.service.CommentApiResponse;
import nl.avans.movieapp.service.MovieApiResponse;
import nl.avans.movieapp.service.MovieAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 */
public class CommentController
        extends BaseMovieAppController
        implements Callback<CommentApiResponse> {

    private final String LOG_TAG = this.getClass().getSimpleName();

    private CommentControllerListener listener;
    private final MovieAPI movieAPI;

    public CommentController(CommentController.CommentControllerListener listener) {
        super();
        this.listener = listener;
        movieAPI = retrofit.create(MovieAPI.class);
    }


    public void loadMovieCommentsById(int movie_id, int pageNr){
        Call<CommentApiResponse> call = movieAPI.loadMovieCommentsById(movie_id);
        Log.d("Loadmoviecommentcall", String.valueOf(movie_id)+ "," + pageNr);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<CommentApiResponse> call, Response<CommentApiResponse> response) {
        Log.d(LOG_TAG, "onResponse() - statuscode: " + response.code());

        if(response.isSuccessful()) {
            Log.d(LOG_TAG, "response: " + response.body());

            // Deserialization
            List<Comment> movies = response.body().getResults();
            listener.onMoviesAvailable(movies);
        } else {
            Log.e(LOG_TAG, "Not successful! Message: " + response.message());
        }
    }

    @Override
    public void onFailure(@NotNull Call<CommentApiResponse> call, Throwable t) {
        Log.e(LOG_TAG, "onFailure - " + t.getMessage());
        listener.onError(t.getMessage());
    }

    public interface CommentControllerListener {
        void onMoviesAvailable(List<Comment> movies);
        void onError(String message);
    }
}
