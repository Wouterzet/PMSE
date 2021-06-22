package nl.avans.movieapp.ui.home;

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
import nl.avans.movieapp.controller.MovieController;
import nl.avans.movieapp.domain.Movie;
import nl.avans.movieapp.repository.MovieRepository;

public class HomeViewModel extends AndroidViewModel
        implements MovieController.MovieControllerListener {

    private final String LOG_TAG = this.getClass().getSimpleName();
    private MutableLiveData<Integer> mPageNr;
    private MutableLiveData<ArrayList<Movie>> mMovies = null;
    private MovieRepository mMovieRepository;
    private Application application;

    public HomeViewModel(Application application) {
        super(application);
        this.application = application;
        this.mPageNr = new MutableLiveData<>(1);
        this.mMovieRepository = new MovieRepository(application);
    }

    public LiveData<Integer> getPageNr() {
        return mPageNr;
    }

    public LiveData<ArrayList<Movie>> getMovies() {
        Log.d(LOG_TAG, "getMovies");
        if(mMovies == null) {
             mMovies = new MutableLiveData<>();
            loadMovies(this);
        }
        return mMovies;
    }

    private void loadMovies(MovieController.MovieControllerListener listener){
        // Do an asynchronous operation to fetch movies
        Log.d(LOG_TAG, "loadMovies");
        MovieController movieController = new MovieController(listener);
        movieController.loadTrendingMoviesPerWeek(this.mPageNr.getValue());
    }

    @Override
    public void onMoviesAvailable(List<Movie> movies) {
        this.mMovies.setValue((ArrayList<Movie>) movies);
        // Save in the database
        // this.mMovieRepository.clear();
        this.mMovieRepository.insertAll(movies);
    }

    Observer observer = new Observer<List<Movie>>() {
        @Override
        public void onChanged(List<Movie> movies) {
            Log.d(LOG_TAG, "getAllMovies().onChanged() - movies = " + movies.toString());
            mMovies.setValue((ArrayList<Movie>) movies);
        }
    };

    @Override
    public void onError(String message) {
        Log.w(LOG_TAG, "Error occurred getting the movies: " + message);
        // No connection to the internet? Get movies from the database.
        // Use observeForever since we do not have access to getLifeCycleOwner()
        this.mMovieRepository.getAllMovies().observeForever(observer);
    }

    @Override
    protected void onCleared() {
        // Important! Clean up the observeForever call!
        this.mMovieRepository.getAllMovies().removeObserver(observer);
        super.onCleared();
    }
}
