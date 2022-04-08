package nl.avans.movieapp.ui.movie;


import android.content.Intent;
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

import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

import nl.avans.movieapp.R;
import nl.avans.movieapp.domain.Comment;
import nl.avans.movieapp.domain.Movie;
import nl.avans.movieapp.domain.Trailer;
import nl.avans.movieapp.ui.gallery.CreateMovieListDialog;
import nl.avans.movieapp.ui.movie.addRating.AddRatingDialog;
import nl.avans.movieapp.ui.movie.addToList.AddMovieToListDialog;
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
private TrailerViewModel trailerViewModel;
private ArrayList<Comment> mMovies = new ArrayList<>();
private ArrayList<Trailer> mTrailers = new ArrayList<>();
private String trailer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Movie mMovie = (Movie) getIntent().getSerializableExtra("Movie");
        setContentView(R.layout.activity_movie_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        String title = "Kinepolis";
        setSupportActionBar(toolbar);
        toolbar.setTitle(title);
        commentViewModel = new ViewModelProvider(this).get(CommentViewModel.class);
        trailerViewModel = new ViewModelProvider(this).get(TrailerViewModel.class);
        commentViewModel.setId(mMovie.getId());
        trailerViewModel.setId(mMovie.getId());
        Log.d("MovieID", String.valueOf(mMovie.getId()));
        trailerViewModel.getTrailers().observe(this, new Observer<ArrayList<Trailer>>() {
            @Override
            public void onChanged(ArrayList<Trailer> trailers) {
                mTrailers = trailers;
                getTrailerLink(mTrailers);
            }
        });
        commentViewModel.getComments().observe(this, new Observer<ArrayList<Comment>>() {
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
        mTitle.setText(mMovie.getTitle());
        mOverview = (TextView) findViewById(R.id.tv_overview);
        mOverview.setText(mMovie.getOverview());
        mRating = (TextView) findViewById(R.id.tv_rating);
        mRating.setText(String.valueOf("Rating: "+mMovie.getVote_average()));
        mGenre = (TextView) findViewById(R.id.tv_genre);
        mGenre.setText(String.valueOf("Genre: "+mMovie.getVote_average()));
        mReleaseYear = (TextView) findViewById(R.id.tv_releaseYear);
        mReleaseYear.setText(String.valueOf("Release year: "+mMovie.getRelease_date().substring(0, 4)));
        Picasso.get()
                .load(mMovie.getBackdrop_path())
                .resize(1200, 750)
                .centerInside()
                .into(mBanner);
        Log.d("Test", mMovie.toString());
        mRecyclerView.setHasFixedSize(true);


        Button button = findViewById(R.id.add_rating);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddRatingDialog dialog = new AddRatingDialog(mMovie.getId());
                dialog.show(getSupportFragmentManager(), "AddRating");
            }
        });
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddMovieToListDialog dialog = new AddMovieToListDialog(mMovie);
                dialog.show(getSupportFragmentManager(), "CreateNewList");
            }
        });
    }
    private void getTrailerLink(ArrayList<Trailer> trailers){
        if (trailers.size() == 0){
            trailer = "N/A";
        }else {
            for (Trailer x : trailers
            ) {
                if (x.getType().equals("Trailer")  && x.getOfficial()) {
                    trailer ="https://www.youtube.com/watch?v=" + x.getKey();
                    mGenre.setText("Trailer: "+ String.valueOf(trailer));
                    mGenre.setMovementMethod(LinkMovementMethod.getInstance());
                }
            }
            if (trailer == null) {

                trailer = "https://www.youtube.com/watch?v=" + trailers.get(0).getKey();
                mGenre.setText("Trailer: "+ String.valueOf(trailer));
                mGenre.setMovementMethod(LinkMovementMethod.getInstance());
            }
        }
    }
}