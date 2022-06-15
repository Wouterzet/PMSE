package nl.avans.movieapp.ui.movie;

import android.app.Application;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import nl.avans.movieapp.controller.TrailerController;
import nl.avans.movieapp.domain.Trailer;

public class TrailerViewModel extends AndroidViewModel
        implements TrailerController.TrailerControllerListener {

    private final String LOG_TAG = this.getClass().getSimpleName();
    private MutableLiveData<ArrayList<Trailer>> mTrailer = null;


    private Application application;
    private int id;


    public TrailerViewModel(Application application) {
        super(application);
        this.application = application;
    }
    public void setId(int id) {
        this.id = id;
    }

    public LiveData<ArrayList<Trailer>> getTrailers() {
        Log.d(LOG_TAG, "getMovies");
        if(mTrailer == null) {
            mTrailer = new MutableLiveData<>();
            loadTrailers(this);
        }
        return mTrailer;
    }
// Deze moet ik nameken denl

    private void loadTrailers(TrailerController.TrailerControllerListener listener){
        // Do an asynchronous operation to fetch movies
        Log.d(LOG_TAG, "loadMovies");
        TrailerController trailerController = new TrailerController(listener);
        trailerController.loadTrailersForMovie(id);
        Log.d("MovieID", String.valueOf(id));
    }


    @Override
    public void onTrailersAvailable(List<Trailer> trailers) {
        this.mTrailer.setValue((ArrayList<Trailer>) trailers);
        // Save in the database

    }

    Observer observer = new Observer<List<Trailer>>() {
        @Override
        public void onChanged(List<Trailer> trailer) {
            Log.d(LOG_TAG, "getAllComments().onChanged() - movies = " + trailer.toString());
            mTrailer.setValue((ArrayList<Trailer>) trailer);
        }
    };


    @Override
    public void onError(String message) {
        Log.w(LOG_TAG, "Error occurred getting the comments: " + message);
        // No connection to the internet? Get comments from the database.
        // Use observeForever since we do not have access to getLifeCycleOwner()
//        this.mCommentRepository.getAllComments().observeForever(observer);

    }

    @Override
    protected void onCleared() {
        // Important! Clean up the observeForever call!
//        this.mCommentRepository.getAllComments().removeObserver(observer);
        super.onCleared();
    }
}
