package nl.avans.movieapp.ui.movielist;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;

import nl.avans.movieapp.R;
import nl.avans.movieapp.domain.Movie;
import nl.avans.movieapp.ui.movie.MovieDetailActivity;

public class MoviePageFragment extends Fragment
        implements MoviePageGridAdapter.OnMovieSelectionListener {
    private final String LOG_TAG = this.getClass().getSimpleName();

    private MoviePageViewModel moviePageViewModel;
    private ArrayList<Movie> mMovies = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private MoviePageGridAdapter mMoviesGridAdapter;

    private static final int ONE_COLUMN = 1;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        int numGridColumns = ONE_COLUMN;
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(
                container.getContext(), numGridColumns);
        mRecyclerView = root.findViewById(R.id.home_movies_grid);
        mRecyclerView.setLayoutManager(layoutManager);
        mMoviesGridAdapter = new MoviePageGridAdapter(this);
        mRecyclerView.setAdapter(mMoviesGridAdapter);

        moviePageViewModel = new ViewModelProvider(this).get(MoviePageViewModel.class);
        moviePageViewModel.getMovies().observe(getViewLifecycleOwner(), new Observer<ArrayList<Movie>>() {
            @Override
            public void onChanged(@Nullable ArrayList<Movie> movies) {
                Log.d(LOG_TAG, "onChanged");
                mMovies = movies;
                mMoviesGridAdapter.setMovieList(mMovies);
            }
        });



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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_movies_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onMovieSelected(int position) {
        Log.d(LOG_TAG, "onMovieSelected at pos " + position);

        Intent intent = new Intent(getContext(), MovieDetailActivity.class);
        intent.putExtra("Movie", mMovies.get(position));
        getContext().startActivity(intent);
    }
}
