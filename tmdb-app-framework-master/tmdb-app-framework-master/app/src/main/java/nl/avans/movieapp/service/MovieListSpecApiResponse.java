package nl.avans.movieapp.service;

import java.util.List;

import nl.avans.movieapp.domain.Movie;
import nl.avans.movieapp.domain.MovieList;
import nl.avans.movieapp.domain.SpecList;

public class MovieListSpecApiResponse {
    private SpecList results;

    public MovieListSpecApiResponse(SpecList results) {
        this.results = results;
    }

    public SpecList getResults() {
        return results;
    }

    @Override
    public String toString() {
        return "MovieListsApiResponse{" +
                ", results=" + results +
                '}';
    }
}
