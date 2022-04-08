package nl.avans.movieapp.service;

import java.util.List;

import nl.avans.movieapp.domain.Movie;
import nl.avans.movieapp.domain.Trailer;

public class TrailerApiResponse  {
    private static int page2;
        private int page;
        private List<Trailer> results;

        public TrailerApiResponse( List<Trailer> results) {

            this.results = results;

        }

        public static int getPage() {
            return page2;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public List<Trailer> getResults() {
            return results;
        }

        public void setResults(List<Trailer> results) {
            this.results = results;
        }

        @Override
        public String toString() {
            return "TrailerApiResponse{" +

                    ", results=" + results +
                    '}';
        }
}
