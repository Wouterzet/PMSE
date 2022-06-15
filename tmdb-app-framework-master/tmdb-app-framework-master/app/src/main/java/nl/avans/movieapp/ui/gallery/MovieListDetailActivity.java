package nl.avans.movieapp.ui.gallery;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.List;

import nl.avans.movieapp.R;
import nl.avans.movieapp.controller.MovieListSpecController;
import nl.avans.movieapp.controller.RemoveMovieController;
import nl.avans.movieapp.domain.Movie;
import nl.avans.movieapp.domain.MovieList;
import nl.avans.movieapp.ui.movie.MovieDetailActivity;

public class MovieListDetailActivity extends AppCompatActivity implements Serializable, MovieListDetailAdapter.OnMovieSelectionListener {
    private List<Movie> movieList;
    private MovieListDetailViewModel viewModel;
    private RecyclerView mRecyclerView;
    private MovieListDetailAdapter movieListAdapter;
    private MovieListSpecController controller;
    private int listId;
    private LifecycleOwner context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listId = (int) getIntent().getSerializableExtra("List");
        context = this;


        setContentView(R.layout.activity_movie_list_detail);
        Toolbar toolbar = findViewById(R.id.selected_movielist_toolbar);
        toolbar.setTitle((String) getIntent().getSerializableExtra("Name"));
        setSupportActionBar(toolbar);
        movieListAdapter = new MovieListDetailAdapter(movieList, this);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 1);
        mRecyclerView = findViewById(R.id.selected_movielist_recycler);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(movieListAdapter);
        //         Call API request
        viewModel = new ViewModelProvider(this).get(MovieListDetailViewModel.class);
        viewModel.getMovieListById(listId).observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> changedList) {
                Log.d("Dit is een test", String.valueOf(changedList.size()));
                movieList = changedList;
                movieListAdapter.setMovieList(movieList);
            }
        });

    }

    public void onDeleteSelected(int position) {
        RemoveMovieController removeMovieController = new RemoveMovieController();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.app_name);
        builder.setMessage("Do you want to remove this movie from the list?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
                viewModel.removeMovieById(position, movieList.get(position).getId(), listId).observe(context, new Observer<List<Movie>>() {
                    @Override
                    public void onChanged(List<Movie> movies) {
                        movieList = movies;
                        movieListAdapter.setMovieList(movieList);
                    }
                });

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void onMovieSelected(int position) {
        Log.d("Movie Selected", "onMovieSelected at pos " + position);

        Intent intent = new Intent(this, MovieDetailActivity.class);
        intent.putExtra("Movie", movieList.get(position));
        this.startActivity(intent);
    }
}