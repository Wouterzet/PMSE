package nl.avans.movieapp.logic;

import android.util.Log;
import android.widget.Adapter;
import android.widget.Filter;

import nl.avans.movieapp.domain.Movie;
import nl.avans.movieapp.ui.movielist.MoviePageGridAdapter;

import java.util.ArrayList;

public class SearchMovie {
    private final String TAG = getClass().getSimpleName();
    private ArrayList<Movie> filmList;
    private ArrayList<Movie> fullFilmList;
    private MoviePageGridAdapter filmAdapter;

    public SearchMovie(ArrayList<Movie> filmList, MoviePageGridAdapter filmAdapter){
        this.filmList = filmList;
        this.fullFilmList = new ArrayList<>();
        this.filmAdapter = filmAdapter;
    }

    private Filter filter = new Filter() {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Movie> filteredList = new ArrayList<>();
            FilterResults results = new FilterResults();
            if (fullFilmList.isEmpty()) {
                fullFilmList.addAll(filmList);
            }

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(fullFilmList);
                results.values = fullFilmList;
                return results;
            }

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(fullFilmList);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                results.values = filterSearchBar(filterPattern);
                return results;
            }
            results.values = fullFilmList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            //        Log.d(TAG, "publishResults() is aangeroepen");
            filmList.clear();
            filmList.addAll((ArrayList) results.values);
            filmAdapter.notifyDataSetChanged();
        }
    };

    public ArrayList<Movie> filterSearchBar(String constraint) {
        ArrayList<Movie> filteredList = new ArrayList<>();
        String filterPattern = constraint.toLowerCase().trim();

        if (constraint == null) {
            filteredList.addAll(fullFilmList);
            return filteredList;
        }

        for (Movie item : filmList) {
            if (item.getTitle().toLowerCase().contains(filterPattern)) {
                filteredList.add(item);
            }
        }
        filmList.addAll(fullFilmList);
        return filteredList;
    }

    public Filter getFilter(){
        return filter;
    }
}