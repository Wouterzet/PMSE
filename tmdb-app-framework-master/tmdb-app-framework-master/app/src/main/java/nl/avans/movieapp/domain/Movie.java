package nl.avans.movieapp.domain;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import nl.avans.movieapp.controller.MovieController;

@Entity
public class Movie {

    // De attributen moeten overeen komen met de waarden zoals die in de JSON gebruikt worden.
    // Je kunt die attribuutname ook aanpassen, maar dat vraagt finetuning.

    @PrimaryKey
    private int id;

    @NonNull
    private String poster_path;

    private Boolean adult;

    @NonNull
    private String title;

    public Movie(
            @NonNull int id,
            @NonNull String poster_path,
            Boolean adult,
            @NonNull String title
    ) {
        this.id = id;
        this.poster_path = /* MovieController.BASE_POSTER_PATH_URL + */ poster_path;
        this.adult = adult;
        this.title = title;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", poster_path='" + poster_path + '\'' +
                ", adult=" + adult +
                ", title='" + title + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getPoster_path() {
        return MovieController.BASE_POSTER_PATH_URL + poster_path;
    }

    public Boolean getAdult() {
        return adult;
    }

    public String getTitle() {
        return title;
    }

}
