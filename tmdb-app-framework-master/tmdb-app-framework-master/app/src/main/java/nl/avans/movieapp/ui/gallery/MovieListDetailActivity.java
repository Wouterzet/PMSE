package nl.avans.movieapp.ui.gallery;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;

import nl.avans.movieapp.R;
import nl.avans.movieapp.controller.MovieListSpecController;
import nl.avans.movieapp.domain.MovieList;
import nl.avans.movieapp.ui.movie.MovieDetailActivity;

public class MovieListDetailActivity extends AppCompatActivity implements Serializable, MovieListDetailAdapter.OnMovieSelectionListener {
    private MovieList mMovies;
    private RecyclerView mRecyclerView;
    private MovieListDetailAdapter movieListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMovies = (MovieList) getIntent().getSerializableExtra("List");
        setContentView(R.layout.activity_movie_list_detail);
        Toolbar toolbar = findViewById(R.id.selected_movielist_toolbar);
        toolbar.setTitle(mMovies.getName());
        setSupportActionBar(toolbar);
        Log.d("Testn",String.valueOf(mMovies.getName()));
        movieListAdapter = new MovieListDetailAdapter(mMovies, this);
        int numGridColumns = 1;
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, numGridColumns);
        mRecyclerView = findViewById(R.id.selected_movielist_recycler);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(movieListAdapter);


//         Call API request
        MovieListSpecController movieListsController = new MovieListSpecController(movieListAdapter);
        movieListsController.loadMovieListByID(mMovies.getId());
        Log.d("KAas", String.valueOf(mMovies.getId()));







   }
    @Override
    public void onMovieSelected(int position) {
        Log.d("Movie Selected", "onMovieSelected at pos " + position);

        Intent intent = new Intent(this, MovieDetailActivity.class);
        intent.putExtra("Movie", mMovies.getItems().get(position));
        this.startActivity(intent);
    }
}