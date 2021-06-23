package nl.avans.movieapp.ui.tv;

import android.app.Application;
import android.util.AndroidException;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import nl.avans.movieapp.controller.CommentController;
import nl.avans.movieapp.controller.MovieController;
import nl.avans.movieapp.controller.TvController;
import nl.avans.movieapp.domain.Comment;
import nl.avans.movieapp.domain.Movie;
import nl.avans.movieapp.domain.Tv;
import nl.avans.movieapp.repository.MovieRepository;

public class TvViewModel extends AndroidViewModel
        implements TvController.TvControllerListener {

    private final String LOG_TAG = this.getClass().getSimpleName();
    private MutableLiveData<Integer> mPageNr;
    private MutableLiveData<ArrayList<Tv>> mMovies = null;

    private Application application;
    private int id;

    public TvViewModel(Application application) {
        super(application);
        this.application = application;
        this.mPageNr = new MutableLiveData<>(1);
    }

    public void setId(int id) {
        this.id = id;
    }

    public LiveData<Integer> getPageNr() {
        return mPageNr;
    }

    public LiveData<ArrayList<Tv>> getMovies() {
        Log.d(LOG_TAG, "getMovies");
        if(mMovies == null) {
            mMovies = new MutableLiveData<>();
            loadMovies(this);
        }
        return mMovies;
    }
// Deze moet ik nameken denl

    private void loadMovies(TvController.TvControllerListener listener){
        // Do an asynchronous operation to fetch movies
        Log.d(LOG_TAG, "loadMovies");
        TvController movieController = new TvController(listener);
        movieController.loadTvShows();
        Log.d("MovieID", String.valueOf(id));
    }

    @Override
    public void onMoviesAvailable(List<Tv> movies) {
        this.mMovies.setValue((ArrayList<Tv>) movies);
        // Save in the database

    }

    Observer observer = new Observer<List<Tv>>() {
        @Override
        public void onChanged(List<Tv> movies) {
            Log.d(LOG_TAG, "getAllMovies().onChanged() - movies = " + movies.toString());
            mMovies.setValue((ArrayList<Tv>) movies);
        }
    };


    @Override
    public void onError(String message) {
        Log.w(LOG_TAG, "Error occurred getting the movies: " + message);
        // No connection to the internet? Get movies from the database.
        // Use observeForever since we do not have access to getLifeCycleOwner()

    }

    @Override
    protected void onCleared() {
        // Important! Clean up the observeForever call!

        super.onCleared();
    }
}

