package nl.avans.movieapp.controller;


import nl.avans.movieapp.service.MovieAPI;
import nl.avans.movieapp.service.MovieApiResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddRatingController extends BaseMovieAppController implements Callback<MovieApiResponse> {

    private final MovieAPI movieAPI;

    public AddRatingController() {
        super();
        movieAPI = retrofit.create(MovieAPI.class);
    }

    public void addRating(double value, int movieId){
        Call<MovieApiResponse> call = movieAPI.addRating(movieId, value);
        call.enqueue(this);
    }


    @Override
    public void onResponse(Call<MovieApiResponse> call, Response<MovieApiResponse> response) {

    }

    @Override
    public void onFailure(Call<MovieApiResponse> call, Throwable t) {

    }
}
