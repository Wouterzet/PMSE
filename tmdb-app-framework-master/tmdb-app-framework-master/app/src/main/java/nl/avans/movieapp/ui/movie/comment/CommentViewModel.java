package nl.avans.movieapp.ui.movie.comment;

import android.app.Application;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import nl.avans.movieapp.controller.CommentController;
import nl.avans.movieapp.controller.MovieController;
import nl.avans.movieapp.domain.Comment;

public class CommentViewModel extends AndroidViewModel
        implements CommentController.CommentControllerListener {

    private final String LOG_TAG = this.getClass().getSimpleName();
    private MutableLiveData<Integer> mPageNr;
    private MutableLiveData<ArrayList<Comment>> mComments = null;

    private Application application;
    private int id;


    public CommentViewModel(Application application) {
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

    public LiveData<ArrayList<Comment>> getComments() {
        Log.d(LOG_TAG, "getMovies");
        if(mComments == null) {
            mComments = new MutableLiveData<>();
            loadComments(this);
        }
        return mComments;
    }
// Deze moet ik nameken denl

    private void loadComments(CommentController.CommentControllerListener listener){
        // Do an asynchronous operation to fetch movies
        Log.d(LOG_TAG, "loadMovies");
        CommentController commentController = new CommentController(listener);
        commentController.loadMovieCommentsById(id,this.mPageNr.getValue());
        Log.d("MovieID", String.valueOf(id));
    }

    private void loadMovieById(MovieController.MovieControllerListener listener){
        Log.d(LOG_TAG, "loadMovieById");
        MovieController movieController = new MovieController(listener);
        movieController.loadMovieById(id);
        Log.d("MovieID", String.valueOf(id));
    }

    @Override
    public void onMoviesAvailable(List<Comment> comments) {
        this.mComments.setValue((ArrayList<Comment>) comments);
    }

    Observer observer = new Observer<List<Comment>>() {
        @Override
        public void onChanged(List<Comment> comments) {
            Log.d(LOG_TAG, "getAllComments().onChanged() - movies = " + comments.toString());
            mComments.setValue((ArrayList<Comment>) comments);
        }
    };


    @Override
    public void onError(String message) {
        Log.w(LOG_TAG, "Error occurred getting the comments: " + message);
        // No connection to the internet? Get comments from the database.
        // Use observeForever since we do not have access to getLifeCycleOwner()

    }

    @Override
    protected void onCleared() {
        // Important! Clean up the observeForever call!
        super.onCleared();
    }
}

