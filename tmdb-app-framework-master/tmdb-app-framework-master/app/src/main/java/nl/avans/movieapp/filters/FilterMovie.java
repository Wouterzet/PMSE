package nl.avans.movieapp.filters;

import android.util.Log;
import android.widget.Filter;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import nl.avans.movieapp.domain.Movie;
import nl.avans.movieapp.ui.movielist.MoviePageGridAdapter;

public class FilterMovie {
    private final String TAG = getClass().getSimpleName();
    private ArrayList<Movie> fullFilmList = new ArrayList<>();
    private ArrayList<Movie> filteredList;
    private HashMap<String, Integer> genres = new HashMap<String, Integer>() {{
        this.put("Action", 28);
        this.put("Adventure", 12);
        this.put("Animation", 16);
        this.put("Comedy", 35);
        this.put("Crime", 80);
        this.put("Documentary", 99);
        this.put("Drama", 18);
        this.put("Family", 10751);
        this.put("Fantasy", 14);
        this.put("History", 36);
        this.put("Horror", 27);
        this.put("Music", 10402);
        this.put("Mystery", 9648);
        this.put("Romance", 10749);
        this.put("Science Fiction", 878);
        this.put("TV Movie", 10770);
        this.put("Thriller", 53);
        this.put("War", 10752);
        this.put("Western", 37);
    }};

    public FilterMovie(ArrayList<Movie> fullList) {
        this.fullFilmList.addAll(fullList);
        this.filteredList = new ArrayList<>();
    };

    public ArrayList<Movie> filterByGenre(String genre) {
        filteredList.clear();
        int id = genres.get(genre);
        for (Movie x: fullFilmList) {
            if(x.getGenre_ids().contains(id)) {
                filteredList.add(x);
            }
        }
        Log.d("LOG_TAG", "" + filteredList.size());
        return filteredList;
    }

    public ArrayList<Movie> removeFilters() {
        return fullFilmList;
    }
}
