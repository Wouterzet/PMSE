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
import nl.avans.movieapp.domain.MovieList;
import nl.avans.movieapp.ui.movie.MovieDetailActivity;

public class MovieListDetailActivity extends AppCompatActivity implements Serializable, MovieListDetailAdapter.OnMovieSelectionListener {
    private MovieList m;
    private RecyclerView mRecyclerView;
    private MovieListDetailAdapter movieListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        m = (MovieList) getIntent().getSerializableExtra("List");
        setContentView(R.layout.activity_movie_list_detail);
        Toolbar toolbar = findViewById(R.id.selected_movielist_toolbar);
        toolbar.setTitle(m.getName());
        setSupportActionBar(toolbar);
        Log.d("Testn",String.valueOf(m.getName()));
        movieListAdapter = new MovieListDetailAdapter(m, this);
        int numGridColumns = 1;
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, numGridColumns);
        mRecyclerView = findViewById(R.id.selected_movielist_recycler);
        mRecyclerView.setLayoutManager(layoutManager);
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
   }
    @Override
    public void onMovieSelected(int position) {
        Log.d("Movie Selected", "onMovieSelected at pos " + position);

        Intent intent = new Intent(this, MovieDetailActivity.class);
        intent.putExtra("Movie", m.getItems().get(position));
        this.startActivity(intent);
    }
}