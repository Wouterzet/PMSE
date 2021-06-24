package nl.avans.movieapp.ui.gallery;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import nl.avans.movieapp.R;
import nl.avans.movieapp.controller.MovieListSpecController;
import nl.avans.movieapp.controller.MovieListsController;
import nl.avans.movieapp.domain.Movie;
import nl.avans.movieapp.domain.MovieList;
import nl.avans.movieapp.ui.movielist.MoviePageGridAdapter;

/**
 *
 */
public class MovieListDetailAdapter
        extends RecyclerView.Adapter<MovieListDetailAdapter.MovieListsViewHolder>
        implements MovieListSpecController.MovieListsSpecControllerListener, Serializable
{

    private final String LOG_TAG = this.getClass().getSimpleName();
    private final MovieList movieLists;
    private final ArrayList<Movie> movieArrayList = new ArrayList<>();

    public MovieListDetailAdapter(MovieList movieLists) {
        Log.d(LOG_TAG, "Constructor aangeroepen");
        this.movieLists = movieLists;
        ArrayList movieArrayList = movieLists.getItems();
    }

    @NonNull
    @Override
    public MovieListsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(LOG_TAG, "onCreate aangeroepen");

        int layoutIdForListItem = R.layout.movie_list_item;
        final boolean shouldAttachToParentImmediately = false;
        ArrayList movieArrayList = movieLists.getItems();
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        return new MovieListsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieListsViewHolder holder, int position) {
        Movie movie = movieArrayList.get(position);
        Log.d(LOG_TAG, movie.toString());

        holder.movieListName.setText(movie.getTitle());
        Log.d(LOG_TAG, movie.toString());

    }

    @Override
    public int getItemCount() {
        return movieArrayList.size();
    }

    @Override
    public void onMovieListsAvailable(MovieList movieLists) {
        Log.d(LOG_TAG, "We have " + movieLists.getItems() + " items");
        this.movieArrayList.clear();
        this.movieArrayList.addAll(movieLists.getItems());
        notifyDataSetChanged();
    }


    /**
     *
     */
    public class MovieListsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView movieListName;

        public MovieListsViewHolder(@NonNull View itemView) {
            super(itemView);
            movieListName = (TextView) itemView.findViewById(R.id.tv_movielist_griditem_textview);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {


        }
    }
}
