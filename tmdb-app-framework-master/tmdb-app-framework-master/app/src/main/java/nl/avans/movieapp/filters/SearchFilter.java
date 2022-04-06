package nl.avans.movieapp.filters;

import java.util.ArrayList;
import java.util.List;

import me.xdrop.fuzzywuzzy.FuzzySearch;
import me.xdrop.fuzzywuzzy.model.BoundExtractedResult;
import nl.avans.movieapp.domain.Movie;

public class SearchFilter extends FuzzySearch {
    private ArrayList<Movie> fullList;
    private ArrayList<Movie> filterdList;

    public SearchFilter(List<Movie> filedList, String search) {
        List<Movie> movies = filedList;

    }
}
