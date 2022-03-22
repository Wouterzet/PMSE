package nl.avans.movieapp.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import nl.avans.movieapp.dao.AppDatabase;
import nl.avans.movieapp.dao.MovieDao;
import nl.avans.movieapp.dao.TvDao;
import nl.avans.movieapp.domain.Movie;
import nl.avans.movieapp.domain.Tv;

public class TvRepository { private TvDao mTvDao;
    private LiveData<List<Tv>> mTvShows;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples

    public TvRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mTvDao = db.tvDao();
        mTvShows = mTvDao.getAll();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<Tv>> getAllTvShows() {
        return mTvShows;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void insert(Tv tv) {
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mTvDao.insert(tv);
            }
        });
    }

    public void insertAll(List<Tv> tvShows) {
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mTvDao.clear();
                mTvDao.insertAll(tvShows);
            }
        });
    }

    public void clear() {
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mTvDao.clear();
            }
        });
    }

}