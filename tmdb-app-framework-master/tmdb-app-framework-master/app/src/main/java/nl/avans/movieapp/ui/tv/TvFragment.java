package nl.avans.movieapp.ui.tv;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.Serializable;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import nl.avans.movieapp.R;
import nl.avans.movieapp.controller.MovieController;
import nl.avans.movieapp.domain.Movie;
import nl.avans.movieapp.domain.Tv;
import nl.avans.movieapp.ui.movie.MovieDetailActivity;
import nl.avans.movieapp.ui.slideshow.SlideshowViewModel;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;
import static android.content.res.Configuration.ORIENTATION_PORTRAIT;

public class TvFragment
        extends Fragment
        implements TvGridAdapter.OnMovieSelectionListener {

    private final String LOG_TAG = this.getClass().getSimpleName();

    private TvViewModel homeViewModel;
    private ArrayList<Tv> mMovies = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private TvGridAdapter mMoviesGridAdapter;

    private static final int ONE_COLUMN = 1;
    private static final int TWO_COLUMNS = 2;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        int numGridColumns = TWO_COLUMNS;
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(
                container.getContext(), numGridColumns);
        mRecyclerView = root.findViewById(R.id.home_movies_grid);
        mRecyclerView.setLayoutManager(layoutManager);
        mMoviesGridAdapter = new TvGridAdapter();
        mRecyclerView.setAdapter(mMoviesGridAdapter);

        homeViewModel = new ViewModelProvider(this).get(TvViewModel.class);
        homeViewModel.getMovies().observe(getViewLifecycleOwner(), new Observer<ArrayList<Tv>>() {
            @Override
            public void onChanged(@Nullable ArrayList<Tv> movies) {
                Log.d(LOG_TAG, "onChanged");
                mMovies = movies;
                mMoviesGridAdapter.setMovieList(mMovies);
            }
        });

        /*
         * Sets up a SwipeRefreshLayout.OnRefreshListener that is invoked when the user
         * performs a swipe-to-refresh gesture.
         */
        final SwipeRefreshLayout mSwipeRefreshLayout = root.findViewById(R.id.home_movies_swiperefresh);
        mSwipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        Log.i(LOG_TAG, "onRefresh called from SwipeRefreshLayout");
                        // Your action here

                        // Stop the spinner from spinning
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }
        );
        return root;
    }

    @Override
    public void onMovieSelected(int position) {
        Log.d(LOG_TAG, "onMovieSelected at pos " + position);

        Intent intent = new Intent(getContext(), MovieDetailActivity.class);
        intent.putExtra("Movie", mMovies.get(position));
        getContext().startActivity(intent);
    }


}