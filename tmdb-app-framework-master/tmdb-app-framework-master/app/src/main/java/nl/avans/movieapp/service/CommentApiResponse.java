package nl.avans.movieapp.service;

import android.util.Log;

import java.util.List;

import nl.avans.movieapp.domain.Comment;
import nl.avans.movieapp.domain.Movie;

/**
 * Dit object komt overeen met het response zoals je dat van de API in het response ontvangt.
 * Je maakt dus voor een API, voor de JSON die je ontvangt, een specifiek response class.
 */
public class CommentApiResponse {

    private int page;
    private List<Comment> results;

    public CommentApiResponse(int page, List<Comment> results) {
        this.page = page;
        this.results = results;
        Log.d("CommentAPIResponse", toString());
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<Comment> getResults() {
        return results;
    }

    public void setResults(List<Comment> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "ComentApiResopnse{" +
                "page=" + page +
                ", results=" + results +
                '}';
    }
}


