package nl.avans.movieapp.domain;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.lang.reflect.Array;

import nl.avans.movieapp.controller.MovieController;

@Entity
public class Comment implements Serializable {

    // De attributen moeten overeen komen met de waarden zoals die in de JSON gebruikt worden.
    // Je kunt die attribuutname ook aanpassen, maar dat vraagt finetuning.

    @PrimaryKey
    @NonNull
    private String id;

    @NonNull
    private String author;

    @NonNull
    private String content;

    @NonNull
    private String updated_at;


    public Comment(String id, @NonNull String author, @NonNull String content, @NonNull String updated_at) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.updated_at = updated_at;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id;

    }

    public String getId() {
        return id;
    }

    @NonNull
    public String getAuthor() {
        return author;
    }

    @NonNull
    public String getContent() {
        return content;
    }

    @NonNull
    public String getUpdated_at() {
        return updated_at;
    }
}
