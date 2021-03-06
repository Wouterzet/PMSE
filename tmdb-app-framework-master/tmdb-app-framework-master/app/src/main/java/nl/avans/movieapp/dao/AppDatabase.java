package nl.avans.movieapp.dao;

import android.content.Context;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import nl.avans.movieapp.domain.Comment;
import nl.avans.movieapp.domain.Movie;
import nl.avans.movieapp.domain.Tv;

/**
 * Zie ook https://developer.android.com/codelabs/android-room-with-a-view
 */
@Database(entities = {Movie.class, Tv.class, Comment.class}, version = 1)

public abstract class AppDatabase extends RoomDatabase {
    public abstract MovieDao movieDao();
    public abstract TvDao tvDao();
    public abstract CommentDao commentDao();

    private static volatile AppDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "movie_app_database")
                            .build();

                }
            }
        }
        return INSTANCE;
    }

}

