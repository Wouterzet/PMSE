package nl.avans.movieapp.ui.gallery;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import nl.avans.movieapp.R;
import nl.avans.movieapp.controller.MovieListSpecController;
import nl.avans.movieapp.controller.RemoveMovieController;
import nl.avans.movieapp.domain.Movie;
import nl.avans.movieapp.domain.MovieList;
import nl.avans.movieapp.repository.MovieRepository;

public class MovieListDetailViewModel extends AndroidViewModel
        implements MovieListSpecController.MovieListsSpecControllerListener{

    private final String LOG_TAG = this.getClass().getSimpleName();
    private MutableLiveData<Integer> mPageNr;
    private MutableLiveData<List<Movie>> mMovies = null;
    private MovieRepository mMovieRepository;
    private Application application;

    public MovieListDetailViewModel(Application application) {
        super(application);
        this.application = application;
    }

    public LiveData<Integer> getPageNr() {
        return mPageNr;
    }


    public MutableLiveData<List<Movie>> getMovieListById(int id) {
        Log.d(LOG_TAG, "getMovieList");
        if(mMovies == null) {
            mMovies = new MutableLiveData<>();
            loadMovieList(this, id);
        }
        return mMovies;
    }

    public MutableLiveData<List<Movie>> removeMovieById(int position, int movieId, int listId) {
        List<Movie> newList = mMovies.getValue();
        newList.remove(position);
        mMovies.setValue(newList);
        removeMovieFromList(movieId, listId);
        return mMovies;
    }

    private void removeMovieFromList(int movieId, int listId) {
        RemoveMovieController removeMovieController = new RemoveMovieController();
        removeMovieController.removeMovieFromList(movieId, listId);
    }

    private void loadMovieList(MovieListSpecController.MovieListsSpecControllerListener listener, int id){
        // Do an asynchronous operation to fetch movies
        Log.d(LOG_TAG, "loadMovies");
        MovieListSpecController moviePageController = new MovieListSpecController(listener);
        moviePageController.loadMovieListByID(id);
    }




    @Override
    protected void onCleared() {
        // Important! Clean up the observeForever call!
        super.onCleared();
    }

    @Override
    public void onMovieListsAvailable(List<Movie> movieLists) {

        this.mMovies.setValue(movieLists);
    }
}
