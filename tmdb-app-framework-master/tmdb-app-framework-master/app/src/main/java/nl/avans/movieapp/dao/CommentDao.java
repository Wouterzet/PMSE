package nl.avans.movieapp.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import nl.avans.movieapp.domain.Comment;


@Dao
public interface CommentDao {
    @Query("SELECT * FROM comment")
    LiveData<List<Comment>> getAll();


    @Insert
    void insert(Comment comment);

    @Insert
    void insertAll(List<Comment> comments);

    @Delete
    void delete(Comment comment);

    @Query("DELETE FROM comment")
    void clear();
}
