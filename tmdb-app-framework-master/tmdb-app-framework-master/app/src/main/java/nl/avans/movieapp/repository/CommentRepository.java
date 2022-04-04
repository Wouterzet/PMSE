package nl.avans.movieapp.repository;

import android.app.Application;

import java.util.List;

import androidx.lifecycle.LiveData;
import nl.avans.movieapp.dao.AppDatabase;
import nl.avans.movieapp.dao.CommentDao;
import nl.avans.movieapp.dao.MovieDao;
import nl.avans.movieapp.domain.Comment;
import nl.avans.movieapp.domain.Movie;

public class CommentRepository {

    private CommentDao mCommentDao;
    private LiveData<List<Comment>> mComments;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples

    public CommentRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mCommentDao = db.commentDao();
        mComments = mCommentDao.getAll();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<Comment>> getAllComments() {
        return mComments;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void insert(Comment comment) {
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mCommentDao.insert(comment);
            }
        });
    }

    public void insertAll(List<Comment> comments) {
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mCommentDao.clear();
                mCommentDao.insertAll(comments);
            }
        });
    }

    public void clear() {
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mCommentDao.clear();
            }
        });
    }

}
