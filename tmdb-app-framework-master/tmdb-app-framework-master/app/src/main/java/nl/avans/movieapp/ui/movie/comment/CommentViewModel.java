package nl.avans.movieapp.ui.movie.comment;

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
import nl.avans.movieapp.domain.Comment;
import nl.avans.movieapp.domain.Movie;
import nl.avans.movieapp.repository.CommentRepository;
import nl.avans.movieapp.repository.MovieRepository;

public class CommentViewModel extends AndroidViewModel
        implements CommentController.CommentControllerListener {

    private final String LOG_TAG = this.getClass().getSimpleName();
    private MutableLiveData<Integer> mPageNr;
    private MutableLiveData<ArrayList<Comment>> mComments = null;
    private CommentRepository mCommentRepository;

    private Application application;
    private int id;

    public CommentViewModel(Application application) {
        super(application);
        this.application = application;
        this.mPageNr = new MutableLiveData<>(1);
        this.mCommentRepository = new CommentRepository(application);
    }

    public void setId(int id) {
        this.id = id;
    }

    public LiveData<Integer> getPageNr() {
        return mPageNr;
    }

    public LiveData<ArrayList<Comment>> getMovies() {
        Log.d(LOG_TAG, "getMovies");
        if(mComments == null) {
            mComments = new MutableLiveData<>();
            loadMovies(this);
        }
        return mComments;
    }
// Deze moet ik nameken denl

    private void loadMovies(CommentController.CommentControllerListener listener){
        // Do an asynchronous operation to fetch movies
        Log.d(LOG_TAG, "loadMovies");
        CommentController commentController = new CommentController(listener);
        commentController.loadMovieCommentsById(id,this.mPageNr.getValue());
        Log.d("MovieID", String.valueOf(id));
    }

    @Override
    public void onMoviesAvailable(List<Comment> comments) {
        this.mComments.setValue((ArrayList<Comment>) comments);
        // Save in the database
        this.mCommentRepository.clear();
        this.mCommentRepository.insertAll(comments);

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
        this.mCommentRepository.getAllComments().observeForever(observer);

    }

    @Override
    protected void onCleared() {
        // Important! Clean up the observeForever call!
        this.mCommentRepository.getAllComments().removeObserver(observer);
        super.onCleared();
    }
}

