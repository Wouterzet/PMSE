package nl.avans.movieapp.service;

import java.util.List;
import nl.avans.movieapp.domain.Movie;
import nl.avans.movieapp.domain.Tv;

/**
 * Dit object komt overeen met het response zoals je dat van de API in het response ontvangt.
 * Je maakt dus voor een API, voor de JSON die je ontvangt, een specifiek response class.
 */
public class TvDetailApiResponse {


    private Tv results;

    public TvDetailApiResponse(Tv results) {

        this.results = results;
    }



    public Tv getResults() {
        return results;
    }

    public void setResults(Tv results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "MovieApiResponse{" +
                ", results=" + results +
                '}';
    }
}


