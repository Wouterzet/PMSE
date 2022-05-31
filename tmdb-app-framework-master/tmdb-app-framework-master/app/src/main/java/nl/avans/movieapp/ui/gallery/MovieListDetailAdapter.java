package nl.avans.movieapp.ui.gallery;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import nl.avans.movieapp.R;
import nl.avans.movieapp.controller.MovieListSpecController;
import nl.avans.movieapp.domain.Movie;
import nl.avans.movieapp.domain.MovieList;
import nl.avans.movieapp.domain.Tv;

/**
 *
 */
public class MovieListDetailAdapter
        extends RecyclerView.Adapter<MovieListDetailAdapter.MovieListsViewHolder>
        implements MovieListSpecController.MovieListsSpecControllerListener, Serializable
{

    private final String LOG_TAG = this.getClass().getSimpleName();
    private final OnMovieSelectionListener listener;
    private List<Movie> movieArrayList = new ArrayList<>();

    public MovieListDetailAdapter(List<Movie> movieLists, OnMovieSelectionListener listener) {
        Log.d(LOG_TAG, "Constructor aangeroepen");
        this.movieArrayList = movieLists;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MovieListsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(LOG_TAG, "onCreate aangeroepen");

        int layoutIdForListItem = R.layout.movie_list_item;
        final boolean shouldAttachToParentImmediately = false;
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        return new MovieListsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieListsViewHolder holder, int position) {
        Movie movie = movieArrayList.get(position);
        Log.d(LOG_TAG, movie.toString());

        Picasso.get()
                .load(movie.getPoster_path())
                .resize(700, 700)
                .centerInside()
                .into(holder.moviePoster);
        holder.movieName.setText(movie.getTitle());
        holder.movieOverview.setText(movie.getOverview());


    }

    @Override
    public int getItemCount() {
        if (movieArrayList != null) {
            return movieArrayList.size();
        }
        return 0;
    }

    public void setMovieList(List<Movie> movies) {
        Log.d(LOG_TAG, "setMovieList");
        this.movieArrayList = movies;
        this.notifyDataSetChanged();
    }

    @Override
    public void onMovieListsAvailable(List<Movie> movieLists) {
        Log.d(LOG_TAG, "We have " + movieLists + " items");
        this.movieArrayList = movieLists;
        notifyDataSetChanged();
    }


    /**
     *
     */
    public class MovieListsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView movieName;
        public TextView movieOverview;
        public ImageView moviePoster;

        public MovieListsViewHolder(@NonNull View itemView) {
            super(itemView);
            movieName = (TextView) itemView.findViewById(R.id.movielist_griditem_title);
            movieOverview = (TextView) itemView.findViewById(R.id.movielist_griditem_overview);
            moviePoster = (ImageView) itemView.findViewById(R.id.movielist_griditem_poster);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.d(LOG_TAG, "onClick on item " + getAdapterPosition());
            listener.onMovieSelected(getAdapterPosition());

        }
    }
    public interface OnMovieSelectionListener {
        void onMovieSelected(int position);
    }
}
