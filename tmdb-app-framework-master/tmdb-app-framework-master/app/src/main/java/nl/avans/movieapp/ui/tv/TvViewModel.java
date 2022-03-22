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
import nl.avans.movieapp.repository.TvRepository;

public class TvViewModel extends AndroidViewModel
        implements TvController.TvControllerListener {

    private final String LOG_TAG = this.getClass().getSimpleName();
    private MutableLiveData<Integer> mPageNr;
    private MutableLiveData<ArrayList<Tv>> mTvShows = null;
    private TvRepository mTvRepo;
    private Application application;
    private int id;

    public TvViewModel(Application application) {
        super(application);
        this.application = application;
        this.mPageNr = new MutableLiveData<>(1);
        this.mTvRepo = new TvRepository(application);
    }

    public void setId(int id) {
        this.id = id;
    }

    public LiveData<Integer> getPageNr() {
        return mPageNr;
    }

    public LiveData<ArrayList<Tv>> getMovies() {
        Log.d(LOG_TAG, "getMovies");
        if(mTvShows == null) {
            mTvShows = new MutableLiveData<>();
            loadTvShows(this);
        }
        return mTvShows;
    }
// Deze moet ik nameken denl

    private void loadTvShows(TvController.TvControllerListener listener){
        // Do an asynchronous operation to fetch movies
        Log.d(LOG_TAG, "loadMovies");
        TvController tvController = new TvController(listener);
        tvController.loadTvShows();
        Log.d("MovieID", String.valueOf(id));
    }

    @Override
    public void onMoviesAvailable(List<Tv> tvShows) {
        this.mTvShows.setValue((ArrayList<Tv>) tvShows);
        // Save in the database
        this.mTvRepo.clear();
        this.mTvRepo.insertAll(tvShows);

    }

    Observer observer = new Observer<List<Tv>>() {
        @Override
        public void onChanged(List<Tv> tvShows) {
            Log.d(LOG_TAG, "getAllMovies().onChanged() - movies = " + tvShows.toString());
            mTvShows.setValue((ArrayList<Tv>) tvShows);

        }
    };


    @Override
    public void onError(String message) {
        Log.w(LOG_TAG, "Error occurred getting the movies: " + message);
        // No connection to the internet? Get movies from the database.
        // Use observeForever since we do not have access to getLifeCycleOwner()
        this.mTvRepo.getAllTvShows().observeForever(observer);
    }

    @Override
    protected void onCleared() {
        // Important! Clean up the observeForever call!
        this.mTvRepo.getAllTvShows().removeObserver(observer);
        super.onCleared();
    }
}

