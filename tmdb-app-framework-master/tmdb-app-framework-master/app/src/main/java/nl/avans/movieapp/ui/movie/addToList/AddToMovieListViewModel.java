package nl.avans.movieapp.ui.movie.addToList;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.util.ArrayList;
import java.util.List;

import nl.avans.movieapp.controller.MovieListsController;
import nl.avans.movieapp.controller.MoviePageController;
import nl.avans.movieapp.domain.Movie;
import nl.avans.movieapp.domain.MovieList;
import nl.avans.movieapp.repository.MovieRepository;

public class AddToMovieListViewModel extends AndroidViewModel
        implements MovieListsController.MovieListsControllerListener {

    private final String LOG_TAG = this.getClass().getSimpleName();
    private MutableLiveData<ArrayList<MovieList>> movieLists = null;

    public AddToMovieListViewModel(Application application) {
        super(application);
    }

    public LiveData<ArrayList<MovieList>> getMovieLists() {
        Log.d(LOG_TAG, "getMovies");
        if(movieLists == null) {
            movieLists = new MutableLiveData<>();
            loadMovieLists(this);
        }
        return movieLists;
    }

    public void loadMovieLists(MovieListsController.MovieListsControllerListener listener) {
        MovieListsController movieListsController = new MovieListsController(listener);
        movieListsController.loadMovieListsForUser();
    }




    @Override
    public void onMovieListsAvailable(List<MovieList> movieLists) {
        this.movieLists.setValue((ArrayList<MovieList>) movieLists);
    }

    @Override
    public void onMovieListCreated(MovieList newMovieList) {

    }
}
