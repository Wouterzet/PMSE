package nl.avans.movieapp.ui.gallery;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.List;

import nl.avans.movieapp.R;
import nl.avans.movieapp.controller.MovieListSpecController;
import nl.avans.movieapp.domain.Movie;
import nl.avans.movieapp.domain.MovieList;
import nl.avans.movieapp.ui.movie.MovieDetailActivity;

public class MovieListDetailActivity extends AppCompatActivity implements Serializable, MovieListDetailAdapter.OnMovieSelectionListener {
    private List<Movie> movieList;
    private MovieListDetailViewModel viewModel;
    private RecyclerView mRecyclerView;
    private MovieListDetailAdapter movieListAdapter;
    private MovieListSpecController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        movieList = (List<Movie>) getIntent().getSerializableExtra("List");

        Log.d("KAas", String.valueOf(movieList.getId()));
        setContentView(R.layout.activity_movie_list_detail);
        Toolbar toolbar = findViewById(R.id.selected_movielist_toolbar);
        toolbar.setTitle(movieList.getName());
        setSupportActionBar(toolbar);
        Log.d("Testn",String.valueOf(movieList.getName()));
        movieListAdapter = new MovieListDetailAdapter(movieList, this);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 1);
        mRecyclerView = findViewById(R.id.selected_movielist_recycler);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(movieListAdapter);
        //         Call API request
        viewModel = new ViewModelProvider(this).get(MovieListDetailViewModel.class);
        viewModel.getMovieListById(movieList.getId()).observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> changedList) {
                Log.d("Dit is een test", String.valueOf(changedList.size()));
                movieList = changedList;
                movieListAdapter.setMovieList(movieList.getItems());
            }
        });

    }

    @Override
    public void onMovieSelected(int position) {
        Log.d("Movie Selected", "onMovieSelected at pos " + position);

        Intent intent = new Intent(this, MovieDetailActivity.class);
        intent.putExtra("Movie", movieList.getItems().get(position));
        this.startActivity(intent);
    }
}