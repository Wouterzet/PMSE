package nl.avans.movieapp.ui.movie;


import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;

import nl.avans.movieapp.R;
import nl.avans.movieapp.domain.Movie;

public class MovieDetailActivity extends AppCompatActivity implements Serializable {
private TextView mTitle;
private ImageView mBanner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_movie_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        String title = "Kinepolis";
        setSupportActionBar(toolbar);
        toolbar.setTitle(title);


        Movie m = (Movie) getIntent().getSerializableExtra("Movie");
        mBanner = (ImageView) findViewById(R.id.iv_banner);
        mTitle = (TextView) findViewById(R.id.tv_title);
        mTitle.setText(m.getTitle());
        Picasso.get()
                .load(m.getBackdrop_path())
                .resize(1200, 750)
                .centerInside()
                .into(mBanner);




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