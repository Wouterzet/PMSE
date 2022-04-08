package nl.avans.movieapp.domain;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

import nl.avans.movieapp.controller.MovieController;

@Entity
public class Movie implements Serializable {

    // De attributen moeten overeen komen met de waarden zoals die in de JSON gebruikt worden.
    // Je kunt die attribuutname ook aanpassen, maar dat vraagt finetuning.

    @PrimaryKey
    private int id;

    @NonNull
    private String poster_path;
    @NonNull
    private String backdrop_path;

    private Boolean adult;

    private String release_date;
    @NonNull
    private String title;

    private Double vote_average;

    @NonNull
    private String overview;
    public Movie(
            @NonNull int id,
            @NonNull String poster_path,
            @NonNull String backdrop_path,
            Boolean adult,

            String release_date,
            @NonNull String overview,
            Double vote_average,
            @NonNull String title
    ) {
        this.overview = overview;
        this.id = id;
        this.vote_average = vote_average;
        this.poster_path = /* MovieController.BASE_POSTER_PATH_URL + */ poster_path;
        this.adult = adult;
        this.title = title;
        this.backdrop_path = /* MovieController.BASE_POSTER_PATH_URL + */ backdrop_path;
        this.release_date = release_date;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", poster_path='" + poster_path + '\'' +
                ", adult=" + adult +
                ", title='" + title + '\'' +
                ", backdrop_path='" + backdrop_path + '\'' +
                ", overview='" + overview + '\'' +
                ", release_date='" + release_date + '\'' +
                ", vote_average='" + vote_average + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getBackdrop_path(){return MovieController.BASE_POSTER_PATH_URL+ backdrop_path;}
    public String getOverview(){return overview;}
    public Double getVote_average(){return vote_average;}

    public String getPoster_path() {
        return MovieController.BASE_POSTER_PATH_URL + poster_path;
    }

    public Boolean getAdult() {
        return adult;
    }

    public String getTitle() {
        return title;
    }

    public String getRelease_date() {
        return release_date;
    }


}
