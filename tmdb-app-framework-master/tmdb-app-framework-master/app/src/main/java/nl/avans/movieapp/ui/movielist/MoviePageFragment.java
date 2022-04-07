package nl.avans.movieapp.ui.movielist;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;

import nl.avans.movieapp.R;
import nl.avans.movieapp.domain.Movie;
import nl.avans.movieapp.filters.SearchFilter;
import nl.avans.movieapp.ui.movie.MovieDetailActivity;

public class MoviePageFragment extends Fragment
        implements MoviePageGridAdapter.OnMovieSelectionListener {
    private final String LOG_TAG = this.getClass().getSimpleName();

    private MoviePageViewModel moviePageViewModel;
    private ArrayList<Movie> mMovies = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private MoviePageGridAdapter mMoviesGridAdapter;
    private SearchFilter searchFilter;
    private ArrayList<Movie> mFullList;


    private static final int ONE_COLUMN = 1;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        setHasOptionsMenu(true);
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
                mFullList= movies;
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
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Enter Title");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(query.length() != 0) {
                    searchFilter = new SearchFilter(mMovies, query);
                    mMovies = searchFilter.filterList();
                    mMoviesGridAdapter.setMovieList(mMovies);
                    return true;
                }else {
                    mMoviesGridAdapter.setMovieList(mFullList);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText.length() != 0){
                    searchFilter = new SearchFilter(mMovies, newText);
                    mMovies = searchFilter.filterList();
                    mMoviesGridAdapter.setMovieList(mMovies);
                    return true;
                }else {
                    mMoviesGridAdapter.setMovieList(mFullList);
                }

                return false;
            }
        });


    }
//        @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        MenuItem menuItem = menu.findItem(R.id.action_search);
//        SearchView searchView = (SearchView) menuItem.getActionView();
//        searchView.setQueryHint("Enter Title");
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                Log.d(LOG_TAG, getCurrentView().getClass().getSimpleName());
//                return false;
//            }
//        });
//        return true;
//    }

//    @SuppressLint("NonConstantResourceId")
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        Log.d(LOG_TAG, "onOptionsItemSelected");
//        // Handle item selection
//        switch (item.getItemId()) {
//            case R.id.filter_on_genre_western:
//            case R.id.filter_on_genre_war:
//            case R.id.filter_on_genre_thriller:
//            case R.id.filter_on_genre_science_fiction:
//            case R.id.filter_on_genre_romance:
//            case R.id.filter_on_genre_mystery:
//            case R.id.filter_on_genre_music:
//            case R.id.filter_on_genre_horror:
//            case R.id.filter_on_genre_history:
//            case R.id.filter_on_genre_fantasy:
//            case R.id.filter_on_genre_family:
//            case R.id.filter_on_genre_drama:
//            case R.id.filter_on_genre_documentary:
//            case R.id.filter_on_genre_crime:
//            case R.id.filter_on_genre_comedy:
//            case R.id.filter_on_genre_animation:
//            case R.id.filter_on_genre_adventure:
//            case R.id.filter_on_genre_action:
//            case R.id.filter_on_genre_all:
//
//                return true;
//
//            default:
//                Log.d(LOG_TAG, "default switch option");
//                return super.onOptionsItemSelected(item);
//        }
//    }


    @Override
    public void onMovieSelected(int position) {
        Log.d(LOG_TAG, "onMovieSelected at pos " + position);

        Intent intent = new Intent(getContext(), MovieDetailActivity.class);
        intent.putExtra("Movie", mMovies.get(position));
        getContext().startActivity(intent);
    }
}
