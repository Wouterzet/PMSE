package nl.avans.movieapp.filters;

import android.widget.Filter;

import java.util.ArrayList;
import java.util.Date;

import nl.avans.movieapp.domain.Movie;

public class FilterMovie {
    private final String TAG = getClass().getSimpleName();
    private ArrayList<Movie> filmList;
    private ArrayList<Movie> fullFilmList;
    private Filter filter;
    public FilterMovie(ArrayList<Movie> fullList) {
        this.fullFilmList = fullList;
        this.filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                return null;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

            }
    };

    }

    public Filter getFilter(){
        return filter;
    }

    public ArrayList<Movie> filterGenre(String genreConstraint){
        return null;
    }

    public ArrayList<Movie> filterRating(Double ratingConstraint){
        return null;
    }

    public ArrayList<Movie> filterReleaseDate(Date dateConstraint){
        return null;
    }
    }
