package nl.avans.movieapp.ui.gallery;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import nl.avans.movieapp.controller.MovieListSpecController;
import nl.avans.movieapp.domain.MovieList;
import nl.avans.movieapp.repository.MovieRepository;

public class MovieListDetailViewModel extends AndroidViewModel
        implements MovieListSpecController.MovieListsSpecControllerListener{

    private final String LOG_TAG = this.getClass().getSimpleName();
    private MutableLiveData<Integer> mPageNr;
    private MutableLiveData<MovieList> mMovies = null;
    private MovieRepository mMovieRepository;
    private Application application;

    public MovieListDetailViewModel(Application application) {
        super(application);
        this.application = application;
    }

    public LiveData<Integer> getPageNr() {
        return mPageNr;
    }


    public MutableLiveData<MovieList> getMovieListById(int id) {
        Log.d(LOG_TAG, "getMovieList");
        if(mMovies == null) {
            mMovies = new MutableLiveData<>();
            loadMovieList(this, id);
        }
        return mMovies;
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
    public void onMovieListsAvailable(MovieList movieLists) {

        this.mMovies.setValue(movieLists);
    }
}
