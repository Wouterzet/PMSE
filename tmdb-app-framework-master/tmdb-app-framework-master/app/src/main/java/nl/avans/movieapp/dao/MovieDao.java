package nl.avans.movieapp.dao;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import nl.avans.movieapp.domain.Movie;

@Dao
public interface MovieDao {
    @Query("SELECT * FROM movie")
    LiveData<List<Movie>> getAll();

    @Query("SELECT * FROM movie WHERE title LIKE :title LIMIT 1")
    Movie findByTitle(String title);

    @Insert
    void insert(Movie movie);

    @Insert
    void insertAll(List<Movie> movies);

    @Delete
    void delete(Movie movie);

    @Query("DELETE FROM movie")
    void clear();
}
