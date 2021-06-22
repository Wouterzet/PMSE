package nl.avans.movieapp.ui.movie;


import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

import java.io.Serializable;

import nl.avans.movieapp.R;
import nl.avans.movieapp.domain.Movie;

public class MovieDetailActivity extends AppCompatActivity implements Serializable {
private TextView mTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        Movie m = (Movie) getIntent().getSerializableExtra("Movie");
        mTitle = (TextView) findViewById(R.id.tv_title);
        mTitle.setText(m.getTitle());




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