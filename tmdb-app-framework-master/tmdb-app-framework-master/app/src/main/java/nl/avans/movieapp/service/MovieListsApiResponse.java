package nl.avans.movieapp.service;

import java.util.List;

import nl.avans.movieapp.domain.Movie;
import nl.avans.movieapp.domain.MovieList;

/**
 * Dit object komt overeen met het response zoals je dat van de API in het response ontvangt.
 * Je maakt dus voor een API, voor de JSON die je ontvangt, een specifiek response class.
 */
public class MovieListsApiResponse {

    private int page;
    private List<MovieList> results;

    public MovieListsApiResponse(int page, List<MovieList> results) {
        this.page = page;
        this.results = results;
    }

    public List<MovieList> getResults() {
        return results;
    }

    @Override
    public String toString() {
        return "MovieListsApiResponse{" +
                "page=" + page +
                ", results=" + results +
                '}';
    }
}


