package nl.avans.movieapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.Menu;
import android.view.View;

import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.widget.SearchView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import nl.avans.movieapp.ui.settings.SettingsActivity;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private final String LOG_TAG = this.getClass().getSimpleName();

    public View getCurrentView() {
        return this.getWindow().getDecorView().findViewById(android.R.id.content).getRootView();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        String title = "Kinepolis";
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                 R.id.nav_list, R.id.nav_movie, R.id.nav_tv)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        toolbar.setTitle(title);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
////        getMenuInflater().inflate(R.menu.main, menu);
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
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

}