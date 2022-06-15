package nl.avans.movieapp.service;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import nl.avans.movieapp.domain.Movie;
import nl.avans.movieapp.domain.MovieList;

public class MovieListSpecApiResponse {
    private String created_by;
    private List<Movie> items;
    public MovieListSpecApiResponse(String created_by,List<Movie> items) { this.items = items; this.created_by = created_by;}

    public List<Movie> getResults() {
        return items;
    }

    @Override
    public String toString() {
        return "MovieListsApiResponse{" +
                ", results=" + items +
                '}';
    }
}
