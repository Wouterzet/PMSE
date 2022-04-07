package nl.avans.movieapp.filters;

import android.util.Log;
import android.widget.Filter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import nl.avans.movieapp.domain.Movie;
import nl.avans.movieapp.ui.movielist.MoviePageGridAdapter;

public class FilterMovie {
    private final String TAG = getClass().getSimpleName();
    private ArrayList<Movie> entireFilmList;
    private ArrayList<Movie> fullFilmList;
    private Filter filter;
    private List<Movie> filteredList;
    private MoviePageGridAdapter mMoviePageGridAdapter = new MoviePageGridAdapter();
    public FilterMovie(ArrayList<Movie> fullList, List<Movie> filteredList) {
        this.fullFilmList = fullList;
        this.filteredList = filteredList;
        this.filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                String strConstraint = constraint.toString();

                if(entireFilmList.isEmpty()){
                    entireFilmList.addAll(fullFilmList);
                }

                if(strConstraint.equals("All")){
                    results.values = entireFilmList;
                    return results;
                }else if(isGenre(strConstraint)){
//                    filterGenre(strConstraint);
                }else if(isRating(strConstraint)){
                    Double dblConstraint = Double.parseDouble(strConstraint);
//                    filterRating(dblConstraint);
                }else{
                    filterReleaseDate(strConstraint);
                }

                results.values = filteredList;
                return results;
            }


            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

            }
    };

    }

    public Filter getFilter(){
        return filter;
    }

//    public List<Movie> filterGenre(String genreConstraint){
//        Log.d(TAG, "filterGenre is aangeroepen.");
//        this.filteredList.clear();
//
//        for(Movie film: this.entireFilmList){
//            if(film.getGenre().equals(genreConstraint)){
//                this.filteredList.add(film);
//            }
//        }
//        if(this.filteredList != null){
//            return this.filteredList;
//        }
//        return null;
//    }
//
//    public ArrayList<Movie> filterRating(Double ratingConstraint){
//
//        return null;
//    }

    public ArrayList<Movie> filterReleaseDate(String dateConstraint){

        return null;
    }

    public boolean isGenre(String strConstraint) {
        String[] possibleGenres = {"Action", "Adventure", "Animation", "Comedy", "Crime", "Documentary", "Drama",
                "Family", "Fantasy", "History", "Horror", "Music", "Mystery", "Romance", "Science Fiction", "Thriller",
                "TV Movie", "War", "Western"};

        for(String genre: possibleGenres){
            if(strConstraint.contains(genre)){
                return true;
            }
        }

        return false;
    }

    public boolean isRating(String strConstraint) {
        try {
            Double.parseDouble(strConstraint);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    }
