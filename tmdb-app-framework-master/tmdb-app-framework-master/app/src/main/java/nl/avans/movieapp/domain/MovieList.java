package nl.avans.movieapp.domain;

import java.io.Serializable;
import java.util.ArrayList;

public class MovieList implements Serializable {

    private String name;
    private String description;
    private int id;
    private ArrayList<Movie> items;
    private int item_count;

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Movie> getItems() {
        return items;
    }

    public MovieList(String name, String description, int id, ArrayList<Movie> items, int item_count) {
        this.name = name;
        this.description = description;
        this.id = id;
        this.items = items;
        this.item_count = item_count;
    }

    // To create a new MovieList
    public MovieList(String name, String description) {
        this(name, description, 0, null, 0);
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "MovieList{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}

