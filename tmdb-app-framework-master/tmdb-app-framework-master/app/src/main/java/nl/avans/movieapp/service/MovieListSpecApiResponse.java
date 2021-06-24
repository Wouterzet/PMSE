package nl.avans.movieapp.service;

import java.util.List;

import nl.avans.movieapp.domain.Movie;
import nl.avans.movieapp.domain.MovieList;

public class MovieListSpecApiResponse {
    private MovieList results;

    public MovieListSpecApiResponse(MovieList results) {
        this.results = results;
    }

    public MovieList getResults() {
        return results;
    }

    @Override
    public String toString() {
        return "MovieListsApiResponse{" +
                ", results=" + results +
                '}';
    }
}
