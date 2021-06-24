package nl.avans.movieapp.ui.movie;


import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
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

import nl.avans.movieapp.R;
import nl.avans.movieapp.domain.Comment;
import nl.avans.movieapp.domain.Movie;
import nl.avans.movieapp.ui.home.HomeGridAdapter;
import nl.avans.movieapp.ui.home.HomeViewModel;
import nl.avans.movieapp.ui.movie.comment.CommentGridAdapter;
import nl.avans.movieapp.ui.movie.comment.CommentViewModel;

public class MovieDetailActivity extends AppCompatActivity implements Serializable {
private TextView mTitle;
private ImageView mBanner;
private  TextView mOverview;
private  TextView mRating;
private  TextView mGenre;
private  TextView mRuntime;
private  TextView mCountry;
private  TextView mReleaseYear;
private RecyclerView mRecyclerView;
private CommentGridAdapter mCommentGridAdapter;
private CommentViewModel commentViewModel;
private ArrayList<Comment> mMovies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Movie m = (Movie) getIntent().getSerializableExtra("Movie");
        setContentView(R.layout.activity_movie_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        String title = "Kinepolis";
        setSupportActionBar(toolbar);
        toolbar.setTitle(title);
        commentViewModel = new ViewModelProvider(this).get(CommentViewModel.class);
        commentViewModel.setId(m.getId());
        Log.d("MovieID", String.valueOf(m.getId()));
        commentViewModel.getMovies().observe(this, new Observer<ArrayList<Comment>>() {
            @Override
            public void onChanged(@Nullable ArrayList<Comment> movies) {
                Log.d("help", "onChanged");
                mMovies = movies;
                mCommentGridAdapter.setMovieList(mMovies);
            }
        });
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(
                this,1);
        mRecyclerView = findViewById(R.id.comment_recyclerView);
        mRecyclerView.setLayoutManager(layoutManager);
        mCommentGridAdapter = new CommentGridAdapter();
        mRecyclerView.setAdapter(mCommentGridAdapter);



        mBanner = (ImageView) findViewById(R.id.iv_banner);
        mTitle = (TextView) findViewById(R.id.tv_title);
        mTitle.setText(m.getTitle());
        mOverview = (TextView) findViewById(R.id.tv_overview);
        mOverview.setText(m.getOverview());
        mRating = (TextView) findViewById(R.id.tv_rating);
        mRating.setText(String.valueOf("Rating: "+m.getVote_average()));
        mGenre = (TextView) findViewById(R.id.tv_genre);
        mGenre.setText(String.valueOf("Genre: "+m.getVote_average()));
        mRuntime = (TextView) findViewById(R.id.tv_runtime);
        mRuntime.setText(String.valueOf("Runtime: "+m.getVote_average()));
        mCountry = (TextView) findViewById(R.id.tv_country);
        mCountry.setText(String.valueOf("Country: "+m.getVote_average()));
        mReleaseYear = (TextView) findViewById(R.id.tv_releaseYear);
        mReleaseYear.setText(String.valueOf("Release year: "+m.getRelease_date().substring(0, 4)));
        Picasso.get()
                .load(m.getBackdrop_path())
                .resize(1200, 750)
                .centerInside()
                .into(mBanner);
        Log.d("Test", m.toString());
        mRecyclerView.setHasFixedSize(true);



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