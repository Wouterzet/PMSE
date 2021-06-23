package nl.avans.movieapp.domain;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

import nl.avans.movieapp.controller.MovieController;
import nl.avans.movieapp.controller.TvController;

@Entity
public class Tv implements Serializable {

    // De attributen moeten overeen komen met de waarden zoals die in de JSON gebruikt worden.
    // Je kunt die attribuutname ook aanpassen, maar dat vraagt finetuning.

    @PrimaryKey
    private int id;

    @NonNull
    private String poster_path;
    @NonNull
    private String backdrop_path;
    @NonNull
    private String overview;
@NonNull
private String first_air_date;

    @NonNull
    private String name;

    private Double vote_average;


    public Tv(
            @NonNull int id,
            @NonNull String first_air_date,
            @NonNull String poster_path,
            @NonNull String backdrop_path,
            @NonNull String overview,
            Double vote_average,
            @NonNull String name
    ) {
        this.first_air_date = first_air_date;
        this.overview = overview;
        this.id = id;
        this.vote_average = vote_average;
        this.poster_path =  MovieController.BASE_POSTER_PATH_URL +  poster_path;

        this.name = name;
        this.backdrop_path =  MovieController.BASE_POSTER_PATH_URL +  backdrop_path;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", poster_path='" + poster_path + '\'' +
                ", name='" + name + '\'' +
                ", backdrop_path='" + backdrop_path + '\'' +
                ", overview='" + overview + '\'' +
                ", popularity='" + overview + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    @NonNull
    public String getFirst_air_date() { return first_air_date;}

    public String getBackdrop_path(){return TvController.BASE_POSTER_PATH_URL+ backdrop_path;}
    public String getOverview(){return overview;}
    public Double getVote_average(){return vote_average;}
    public String getPoster_path() {
        return TvController.BASE_POSTER_PATH_URL + poster_path;
    }
    public String getName() {return name; }

}
