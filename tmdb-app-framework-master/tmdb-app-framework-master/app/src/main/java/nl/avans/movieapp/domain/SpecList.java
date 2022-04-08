package nl.avans.movieapp.domain;

import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;

public class SpecList implements Serializable {

    private String description;
    @PrimaryKey
    private int id;
    private ArrayList<Movie> items;
    private int item_count;
    private String created_by;
    private String name;
    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<Movie> getItems() {
        return items;
    }

    public String getCreated_by() {
        return created_by;
    }

    public SpecList(String name, String description, int id, ArrayList<Movie> items, int item_count, String created_by) {
        this.created_by = created_by;
        this.name = name;
        this.description = description;
        this.id = id;
        this.items = items;
        this.item_count = item_count;
    }

    // To create a new MovieList
    public SpecList(String name, String description) {
        this(name, description, 0, null, 0,null);
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
                ", id=" + id + "Test"+ created_by+
                '}';
    }
}
