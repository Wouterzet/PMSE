package nl.avans.movieapp.controller;

import android.util.Log;

import nl.avans.movieapp.domain.MovieList;
import nl.avans.movieapp.service.CreateMovieListApiResponse;
import nl.avans.movieapp.service.MovieAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMovieController  extends BaseMovieAppController
        implements Callback<CreateMovieListApiResponse> {
    private final String LOG_TAG = this.getClass().getSimpleName();
    private MovieListsController.MovieListsControllerListener listener;
    private final MovieAPI theMovieDbAPI;
    public AddMovieController(){
        super();
        theMovieDbAPI = retrofit.create(MovieAPI.class);
    }
    public void addMovieToList(int media_id, int listId){
        Call<CreateMovieListApiResponse> call = theMovieDbAPI.addMovieToList( listId,  String.valueOf(media_id));
        call.enqueue(this);
    }

    @Override
    public void onResponse(        Call<CreateMovieListApiResponse> call,
                                   Response<CreateMovieListApiResponse> response) {
        Log.d(LOG_TAG, "onResponse() - statuscode: " + response.code());

        if(response.isSuccessful()) {
            Log.d(LOG_TAG, "response: " + response.body());

//            // Deserialization
//            int movieListId = response.body().getList_id();
//            Log.d(LOG_TAG, "ListId = " + movieListId);
//            newMovieList.setId(movieListId);

            // We kunnen hier opnieuw een request versturen om de hele lijst van
            // MovieLists nogmaals op te halen, maar handiger is om de lijst die we
            // hebben gemaakt met de ID aan de bestaande lijst toe te voegen.
            // Dat scheelt een request. Risico is wel dat we ergens de synchronisatie
            // met de backend server verliezen.
//            listener.onMovieListCreated(newMovieList);
        } else {
            Log.e(LOG_TAG, "Not successful! Message: " + response.message());
        }
    }

    @Override
    public void onFailure(Call<CreateMovieListApiResponse> call, Throwable t) {
        Log.e(LOG_TAG, "onFailure - " + t.getMessage());
    }
}
