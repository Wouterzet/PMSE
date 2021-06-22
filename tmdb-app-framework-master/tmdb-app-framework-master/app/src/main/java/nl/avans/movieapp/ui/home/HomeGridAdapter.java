package nl.avans.movieapp.ui.home;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import nl.avans.movieapp.R;
import nl.avans.movieapp.controller.MovieController;
import nl.avans.movieapp.domain.Movie;
import nl.avans.movieapp.ui.movie.MovieDetailActivity;

/**
 *
 */
public class HomeGridAdapter
        extends RecyclerView.Adapter<HomeGridAdapter.MoviesGridViewHolder> {

    private final String LOG_TAG = this.getClass().getSimpleName();
    private final ArrayList<Movie> moviesArrayList = new ArrayList<>();
    private final OnMovieSelectionListener listener;

    public HomeGridAdapter(OnMovieSelectionListener listener) {
        Log.d(LOG_TAG, "Constructor aangeroepen");
        this.listener = listener;
    }

    @NonNull
    @Override
    public MoviesGridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         Log.d(LOG_TAG, "onCreateViewHolder aangeroepen");

        int layoutIdForListItem = R.layout.home_movies_grid_item;
        final boolean shouldAttachToParentImmediately = false;

        View view = LayoutInflater.from(parent.getContext()).inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        return new MoviesGridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesGridViewHolder holder, int position) {
        Movie movie = moviesArrayList.get(position);
        // Log.d(LOG_TAG, "onBindViewHolder");

        Picasso.get()
                .load(movie.getPoster_path())
                .resize(700, 700)
                .centerInside()
                .into(holder.mMovieImage);
    }

    @Override
    public int getItemCount() {
        return moviesArrayList.size();
    }

    public void setMovieList(List<Movie> movies) {
        Log.d(LOG_TAG, "setMovieList");
        this.moviesArrayList.clear();
        this.moviesArrayList.addAll(movies);
        this.notifyDataSetChanged();
    }

    public class MoviesGridViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView mMovieImage;

        public MoviesGridViewHolder(@NonNull View itemView) {
            super(itemView);
            mMovieImage = (ImageView) itemView.findViewById(R.id.home_movies_griditem_imageurl);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            Log.d(LOG_TAG, "onClick on item " + getAdapterPosition());
            listener.onMovieSelected(getAdapterPosition());

        }
    }

    public interface OnMovieSelectionListener {
        void onMovieSelected(int position);
    }
}
