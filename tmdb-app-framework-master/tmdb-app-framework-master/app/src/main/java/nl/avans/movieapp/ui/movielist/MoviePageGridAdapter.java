package nl.avans.movieapp.ui.movielist;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import nl.avans.movieapp.R;
import nl.avans.movieapp.domain.Movie;
import nl.avans.movieapp.service.MovieApiResponse;

public class MoviePageGridAdapter
        extends RecyclerView.Adapter<MoviePageGridAdapter.MoviesGridViewHolder> {

    private final String LOG_TAG = this.getClass().getSimpleName();
    private final ArrayList<Movie> moviesArrayList = new ArrayList<>();
    private  OnMovieSelectionListener listener;

    public MoviePageGridAdapter(OnMovieSelectionListener listener) {
        Log.d(LOG_TAG, "Constructor aangeroepen");
        this.listener = listener;
    }
    public MoviePageGridAdapter(){

    }

    @NonNull
    @Override
    public MoviesGridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(LOG_TAG, "onCreateViewHolder aangeroepen");

        int layoutIdForListItem = R.layout.movie_list_grid_item;
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
        if (movie.getTitle() != null) {
            holder.mMovieTitle.setText(movie.getTitle());
        }
        if (movie.getTitle() != null) {
            if (movie.getOverview().length() < 100) {
                holder.mMovieLength.setText(String.valueOf(movie.getOverview()));
            } else {
                holder.mMovieLength.setText(String.valueOf(movie.getOverview().substring(0,100).trim() + "...more info"));
            }
        }
        if (movie.getTitle() != null) {
            holder.mMovieReleaseYear.setText(movie.getRelease_date().substring(0,4));
        }
        if (movie.getTitle() != null) {
            holder.mMovieRating.setText(movie.getVote_average().toString());
        }
        Log.d(LOG_TAG, "onBindViewHolder: " + movie.toString());
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
        public TextView mMovieTitle;
        public TextView mMovieLength;
        public TextView mMovieReleaseYear;
        public TextView mMovieRating;



        public MoviesGridViewHolder(@NonNull View itemView) {
            super(itemView);
            mMovieImage = (ImageView) itemView.findViewById(R.id.home_movies_griditem_imageurl);
            mMovieTitle = (TextView) itemView.findViewById(R.id.home_movies_griditem_textview);
            mMovieLength = (TextView) itemView.findViewById(R.id.home_movies_griditem_lenght);
            mMovieReleaseYear = (TextView) itemView.findViewById(R.id.home_movies_griditem_releaseYear);
            mMovieRating = (TextView) itemView.findViewById(R.id.home_movies_griditem_rating);


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
