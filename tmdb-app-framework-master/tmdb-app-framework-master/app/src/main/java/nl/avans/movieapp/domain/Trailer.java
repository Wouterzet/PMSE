package nl.avans.movieapp.domain;

import androidx.annotation.NonNull;
import androidx.room.PrimaryKey;

import java.io.Serializable;

public class Trailer implements Serializable {
    @PrimaryKey
    private String id;

    private String key;

    @NonNull
    private String type;

    private Boolean official;
    public Trailer(String id, String key, Boolean official, String type){
        this.id = id;
        this.key = key;
        this.type= type;
        this.official = official;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @NonNull
    public String getType() {
        return type;
    }

    public void setType(@NonNull String type) {
        this.type = type;
    }

    public Boolean getOfficial() {
        return official;
    }

    public void setOfficial(Boolean official) {
        this.official = official;
    }

    @Override
    public String toString() {
        return "Trailer{" +
                "id='" + id + '\'' +
                ", key='" + key + '\'' +
                ", type='" + type + '\'' +
                ", official=" + official +
                '}';
    }
}
