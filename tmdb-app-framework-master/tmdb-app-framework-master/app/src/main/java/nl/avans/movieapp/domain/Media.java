package nl.avans.movieapp.domain;

public class Media {
    private String media_id;

    public String getMedia_id() {
        return media_id;
    }

    public void setMedia_id(String media_id) {
        this.media_id = media_id;
    }

    public Media(String media_id) {
        this.media_id = media_id;
    }
}
