package nl.avans.movieapp.filters;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import me.xdrop.fuzzywuzzy.Applicable;
import me.xdrop.fuzzywuzzy.FuzzySearch;
import me.xdrop.fuzzywuzzy.model.BoundExtractedResult;
import me.xdrop.fuzzywuzzy.model.ExtractedResult;
import nl.avans.movieapp.domain.Movie;

public class SearchFilter extends FuzzySearch {
    private List<Movie> filedList;
    private List<String> nameList;
    private String search;
    private ArrayList<Movie> filterdList;


    public SearchFilter(List<Movie> fullList, String search) {
        this.filedList= fullList;
        this.search = search;
        filterdList = new ArrayList<>();
    }
    public ArrayList<Movie> filterList(){
        makeNameList();
        List<ExtractedResult> filteredNames = new ArrayList<>();
        filteredNames = FuzzySearch.extractTop(search, nameList,filedList.size());
        Log.d("Search filter",filteredNames.toString());
        stringsToMovies(filteredNames);
        return filterdList;
    }
//public void filterList(){
//        makeNameList();
//        List<ExtractedResult> filteredNames = new ArrayList<>();
//        filteredNames = FuzzySearch.extractTop(search, nameList,10);
//        Log.d("Search filter",filteredNames.toString());
//    }
    public void stringsToMovies(List<ExtractedResult> filteredNames){
        for (int i =0;filteredNames.size()> i; i++){

            String name = filteredNames.get(i).getString();

            for (Movie x:filedList
                 ) {
                if (x.getTitle() == name){
                    filterdList.add(x);
                }

            }
        }
    }


    public void makeNameList(){
        nameList = new ArrayList<>();
        filedList.forEach(name -> nameList.add(name.getTitle()));

    }





}
