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
import nl.avans.movieapp.controller.MovieListsController;
import nl.avans.movieapp.domain.MovieList;
import nl.avans.movieapp.ui.movielist.MoviePageGridAdapter;

/**
 *
 */
public class MovieListAdapter
        extends RecyclerView.Adapter<MovieListAdapter.MovieListsViewHolder>
        implements MovieListsController.MovieListsControllerListener, Serializable
{

    private final String LOG_TAG = this.getClass().getSimpleName();
    private final ArrayList<MovieList> movieLists;
    private final OnListSelectionListener listener;
    public MovieListAdapter(ArrayList<MovieList> movieLists, OnListSelectionListener listener) {
        Log.d(LOG_TAG, "Constructor aangeroepen");
        this.movieLists = movieLists;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MovieListsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(LOG_TAG, "onCreate aangeroepen");

        int layoutIdForListItem = R.layout.gallery_movielist_item;
        final boolean shouldAttachToParentImmediately = false;

        View view = LayoutInflater.from(parent.getContext()).inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        return new MovieListsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieListsViewHolder holder, int position) {
        MovieList movieList = movieLists.get(position);
        Log.d(LOG_TAG, movieList.toString());

        holder.movieListName.setText(movieList.getName().trim());
    }

    @Override
    public int getItemCount() {
        return movieLists.size();
    }

    @Override
    public void onMovieListsAvailable(List<MovieList> movieLists) {
        Log.d(LOG_TAG, "We have " + movieLists.size() + " items");
        this.movieLists.clear();
        this.movieLists.addAll(movieLists);
        notifyDataSetChanged();
    }

    @Override
    public void onMovieListCreated(MovieList newMovieList) {
        Log.d(LOG_TAG, "onMovieListCreated " + newMovieList.toString());
        this.movieLists.add(newMovieList);
        notifyDataSetChanged();
    }

    /**
     *
     */
    public class MovieListsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView movieListName;

        public MovieListsViewHolder(@NonNull View itemView) {
            super(itemView);
            movieListName = (TextView) itemView.findViewById(R.id.gallery_movielists_listname);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
                listener.onListSelected(getAdapterPosition());

        }
    }
    public interface OnListSelectionListener {
        void onListSelected(int position);
    }
}
