package nl.avans.movieapp.ui.gallery;


import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import nl.avans.movieapp.R;
import nl.avans.movieapp.controller.CommentController;
import nl.avans.movieapp.controller.MovieListsController;
import nl.avans.movieapp.controller.MoviePageController;
import nl.avans.movieapp.controller.TvSpecController;
import nl.avans.movieapp.domain.Comment;
import nl.avans.movieapp.domain.Movie;
import nl.avans.movieapp.domain.MovieList;
import nl.avans.movieapp.domain.Tv;
import nl.avans.movieapp.ui.home.HomeGridAdapter;
import nl.avans.movieapp.ui.home.HomeViewModel;
import nl.avans.movieapp.ui.movie.comment.CommentGridAdapter;
import nl.avans.movieapp.ui.movie.comment.CommentViewModel;
import nl.avans.movieapp.ui.tv.TvViewModel;

public class MovieListDetailActivity extends AppCompatActivity implements Serializable{
    private TextView mTitle;
    private ImageView mBanner;
    private TextView mOverview;
    private TextView mRating;
    private MovieList m;
    private RecyclerView mRecyclerView;
    private MovieListDetailAdapter movieListAdapter;
    private final MovieList movieLists = new MovieList("name", "String description", 2, new ArrayList<>() ,2, "Henk");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        m = (MovieList) getIntent().getSerializableExtra("List");
        setContentView(R.layout.activity_movie_list_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        String title = "Kinepolis";
        setSupportActionBar(toolbar);
        toolbar.setTitle(title);
        mTitle = (TextView) findViewById(R.id.tv_title_movie_list);
        mTitle.setText(m.getName());
        Log.d("Testn",String.valueOf(m.getName()));

        int numGridColumns = 1;
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, numGridColumns);
        mRecyclerView.setLayoutManager(layoutManager);
        movieListAdapter = new MovieListDetailAdapter(movieLists);
        mRecyclerView.setAdapter(movieListAdapter);

        // Call API request
//        MovieListsController movieListsController = new MovieListsController(movieListAdapter);
//        movieListsController.loadMovieListsForUser();
//        commentViewModel = new ViewModelProvider(this).get(TvDetailViewModel.class);
//        commentViewModel.setId(m.getId());
//        Log.d("MovieID", String.valueOf(m.getId()));
//        commentViewModel.getMovies().observe(this, new Observer<Tv>() {
//            @Override
//            public void onChanged(@Nullable Tv tv) {
//                Log.d("help", "onChanged");
//                m = tv;
//            }
//        });
//
//        mBanner = (ImageView) findViewById(R.id.iv_banner);
//        mTitle = (TextView) findViewById(R.id.tv_title);
//        mTitle.setText(m.getName());
//        mOverview = (TextView) findViewById(R.id.tv_overview);
//        mOverview.setText(m.getOverview());
//        mRating = (TextView) findViewById(R.id.tv_rating);
//        mRating.setText(String.valueOf("Rating: "+m.getVote_average()));
//        Picasso.get()
//                .load(m.getBackdrop_path())
//                .resize(1200, 750)
//                .centerInside()
//                .into(mBanner);
//        Log.d("Test", m.toString());
//
//
//
//
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
   }
}