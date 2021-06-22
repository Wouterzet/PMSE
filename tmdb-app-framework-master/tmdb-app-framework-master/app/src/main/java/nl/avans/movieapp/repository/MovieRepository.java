package nl.avans.movieapp.repository;

import android.app.Application;

import java.util.List;

import androidx.lifecycle.LiveData;
import nl.avans.movieapp.dao.AppDatabase;
import nl.avans.movieapp.dao.MovieDao;
import nl.avans.movieapp.domain.Movie;

public class MovieRepository {

    private MovieDao mMovieDao;
    private LiveData<List<Movie>> mMovies;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples

    public MovieRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mMovieDao = db.movieDao();
        mMovies = mMovieDao.getAll();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<Movie>> getAllMovies() {
        return mMovies;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void insert(Movie movie) {
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mMovieDao.insert(movie);
            }
        });
    }

    public void insertAll(List<Movie> movies) {
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mMovieDao.clear();
                mMovieDao.insertAll(movies);
            }
        });
    }

    public void clear() {
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mMovieDao.clear();
            }
        });
    }

}
