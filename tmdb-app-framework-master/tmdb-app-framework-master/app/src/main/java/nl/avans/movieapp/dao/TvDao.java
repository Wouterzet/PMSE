package nl.avans.movieapp.dao;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;


import nl.avans.movieapp.domain.Tv;
@Dao
public interface TvDao {
    @Query("SELECT * FROM tv")
    LiveData<List<Tv>> getAll();

    @Query("SELECT * FROM tv WHERE name LIKE :name LIMIT 1")
    Tv findByName(String name);

    @Insert
    void insert(Tv tv);

    @Insert
    void insertAll(List<Tv> tvShows);

    @Delete
    void delete(Tv tv);

    @Query("DELETE FROM tv")
    void clear();
}
//    @NonNull
//    int id,
//    @NonNull String first_air_date,
//    @NonNull String poster_path,
//    @NonNull String backdrop_path,
//    @NonNull String overview,
//    Double vote_average,
//    @NonNull String name