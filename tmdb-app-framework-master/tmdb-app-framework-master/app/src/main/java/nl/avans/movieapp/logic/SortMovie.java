package nl.avans.movieapp.logic;

import android.util.Log;
import android.widget.Filter;

import nl.avans.movieapp.domain.Movie;
import nl.avans.movieapp.ui.movielist.MoviePageGridAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

public class SortMovie {

    private final String TAG = getClass().getSimpleName();
    private ArrayList<Movie> filmList;
    private ArrayList<Movie> fullFilmList = new ArrayList<>();
    private ArrayList<String> filmTitles = new ArrayList<>();
    private ArrayList<Date> filmDates = new ArrayList<>();
    private ArrayList<Double> filmRating = new ArrayList<>();
    private MoviePageGridAdapter filmAdapter;

    public SortMovie(ArrayList<Movie> filmList, MoviePageGridAdapter filmAdapter) {
        this.filmList = filmList;
        this.filmAdapter = filmAdapter;
        fullFilmList.addAll(filmList);
    }

    private Filter filter = new Filter() {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            //     Log.d(TAG, "performFiltering is aangeroepen");
            ArrayList<Movie> filteredList = new ArrayList<>();

            String strConstraint = constraint.toString();

            if (filmList.isEmpty()) {
                filmList.addAll(fullFilmList);
            }

            if (strConstraint.equals("sortAtoZ")) {
                getAllFilmTitles();
                filteredList = sortAtoZ();
            } else if (strConstraint.equals("sortZtoA")) {
                getAllFilmTitles();
                filteredList = sortZtoA();
            } else if (strConstraint.equals("sortRatingHigh")) {
                filteredList = sortRating(sortHashMapByValuesHighToLow(getFilmRating()));
            } else if (strConstraint.equals("sortRatingLow")) {
                filteredList = sortRating(sortHashMapByValuesLowToHigh(getFilmRating()));
            } else if (strConstraint.equals("sortOldToNew")) {
                try {
                    filteredList = sortReleaseDate(sortHashMapDateByValuesHighToLow(getReleaseDateFilms()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else if (strConstraint.equals("sortNewToOld")) {
                try {
                    filteredList = sortReleaseDate(sortHashMapDateByValuesLowToHigh(getReleaseDateFilms()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filmList.clear();
            filmList.addAll((ArrayList) results.values);
            filmAdapter.notifyDataSetChanged();
        }
    };

    public Filter getFilter() {
        return filter;
    }

    public HashMap<String, Date> getReleaseDateFilms() throws ParseException {
        HashMap<String, Date> filmReleaseDate = new HashMap<>();

        for (Movie x : filmList) {
            filmReleaseDate.put(x.getTitle(), new SimpleDateFormat("yyyy-MM-dd").parse(x.getRelease_date()));
        }
        return filmReleaseDate;
    }

    public ArrayList<String> getAllFilmTitles() {
        for (Movie x : filmList) {
            filmTitles.add(x.getTitle());
        }
        return filmTitles;
    }

    public HashMap<String, Double> getFilmRating() {
        HashMap<String, Double> filmRating = new HashMap<>();

        for (Movie x : filmList) {
            filmRating.put(x.getTitle(), x.getVote_average());
        }
        return filmRating;
    }

    public ArrayList<Movie> sortZtoA() {
        ArrayList<Movie> sortedList = new ArrayList<>();
        Collections.sort(this.filmTitles, Collections.reverseOrder());
        for (String i : filmTitles) {
            for (Movie j : filmList) {
                if (i.equals(j.getTitle())) {
                    sortedList.add(j);
                }
            }
        }
        return sortedList;
    }

    public ArrayList<Movie> sortAtoZ() {
        ArrayList<Movie> sortedList = new ArrayList<>();
        Collections.sort(this.filmTitles);
        for (String i : filmTitles) {
            for (Movie j : filmList) {
                if (i.equals(j.getTitle())) {
                    sortedList.add(j);
                }
            }
        }
        return sortedList;
    }

    public ArrayList<Movie> sortRating(LinkedHashMap<String, Double> sortedHashMap) {
        ArrayList<Movie> sortedList = new ArrayList<>();
        for (String i : sortedHashMap.keySet()) {
            for (Movie j : filmList) {
                if (i.equals(j.getTitle())) {
                    sortedList.add(j);
                }
            }
        }
        return sortedList;
    }

    public ArrayList<Movie> sortReleaseDate(LinkedHashMap<String, Date> stringDateLinkedHashMap)  {
        ArrayList<Movie> sortedList = new ArrayList<>();
        Collections.sort(this.filmDates);
        for (String i : stringDateLinkedHashMap.keySet()) {
            for (Movie j : filmList) {
                if (i.equals(j.getTitle())) {
                    sortedList.add(j);
                }
            }
        }
        return sortedList;
    }

    public LinkedHashMap<String, Double> sortHashMapByValuesLowToHigh(HashMap<String, Double> passedMap) {
        List<String> mapKeys = new ArrayList<>(passedMap.keySet());
        List<Double> mapValues = new ArrayList<>(passedMap.values());
        Collections.sort(mapValues);
        Collections.sort(mapKeys);

        LinkedHashMap<String, Double> sortedMap =
                new LinkedHashMap<>();

        Iterator<Double> valueIt = mapValues.iterator();
        while (valueIt.hasNext()) {
            Double val = valueIt.next();
            Iterator<String> keyIt = mapKeys.iterator();

            while (keyIt.hasNext()) {
                String key = keyIt.next();
                Double comp1 = passedMap.get(key);
                Double comp2 = val;

                if (comp1.equals(comp2)) {
                    keyIt.remove();
                    sortedMap.put(key, val);
                    break;
                }
            }
        }
        return sortedMap;
    }

    public LinkedHashMap<String, Double> sortHashMapByValuesHighToLow(HashMap<String, Double> passedMap) {
        List<String> mapKeys = new ArrayList<>(passedMap.keySet());
        List<Double> mapValues = new ArrayList<>(passedMap.values());
        Collections.sort(mapValues, Collections.reverseOrder());
        Collections.sort(mapKeys, Collections.reverseOrder());

        LinkedHashMap<String, Double> sortedMap =
                new LinkedHashMap<>();

        Iterator<Double> valueIt = mapValues.iterator();
        while (valueIt.hasNext()) {
            Double val = valueIt.next();
            Iterator<String> keyIt = mapKeys.iterator();

            while (keyIt.hasNext()) {
                String key = keyIt.next();
                Double comp1 = passedMap.get(key);
                Double comp2 = val;

                if (comp1.equals(comp2)) {
                    keyIt.remove();
                    sortedMap.put(key, val);
                    break;
                }
            }
        }
        return sortedMap;
    }

    public LinkedHashMap<String, Date> sortHashMapDateByValuesHighToLow(HashMap<String, Date> passedMap) {
        List<String> mapKeys = new ArrayList<>(passedMap.keySet());
        List<Date> mapValues = new ArrayList<>(passedMap.values());
        Collections.sort(mapValues, Collections.reverseOrder());
        Collections.sort(mapKeys, Collections.reverseOrder());

        LinkedHashMap<String, Date> sortedMap =
                new LinkedHashMap<>();

        Iterator<Date> valueIt = mapValues.iterator();
        while (valueIt.hasNext()) {
            Date val = valueIt.next();
            Iterator<String> keyIt = mapKeys.iterator();

            while (keyIt.hasNext()) {
                String key = keyIt.next();
                Date comp1 = passedMap.get(key);
                Date comp2 = val;

                if (comp1.equals(comp2)) {
                    keyIt.remove();
                    sortedMap.put(key, val);
                    break;
                }
            }
        }
        return sortedMap;
    }

    public LinkedHashMap<String, Date> sortHashMapDateByValuesLowToHigh(HashMap<String, Date> passedMap) {
        List<String> mapKeys = new ArrayList<>(passedMap.keySet());
        List<Date> mapValues = new ArrayList<>(passedMap.values());
        Collections.sort(mapValues);
        Collections.sort(mapKeys);

        LinkedHashMap<String, Date> sortedMap =
                new LinkedHashMap<>();

        Iterator<Date> valueIt = mapValues.iterator();
        while (valueIt.hasNext()) {
            Date val = valueIt.next();
            Iterator<String> keyIt = mapKeys.iterator();

            while (keyIt.hasNext()) {
                String key = keyIt.next();
                Date comp1 = passedMap.get(key);
                Date comp2 = val;

                if (comp1.equals(comp2)) {
                    keyIt.remove();
                    sortedMap.put(key, val);
                    break;
                }
            }
        }
        return sortedMap;
    }
}