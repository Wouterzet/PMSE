package nl.avans.movieapp.service;

import java.util.List;
import nl.avans.movieapp.domain.Movie;

/**
 * Dit object komt overeen met het response zoals je dat van de API in het response ontvangt.
 * Je maakt dus voor een API, voor de JSON die je ontvangt, een specifiek response class.
 */
public class MovieApiResponse {

    private static int page2;
    private int page;
    private List<Movie> results;

    public MovieApiResponse(int page, List<Movie> results) {
        this.page = page;
        this.results = results;
        this.page2 = page;
    }

    public static int getPage() {
        return page2;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "MovieApiResponse{" +
                "page=" + page +
                ", results=" + results +
                '}';
    }
}


