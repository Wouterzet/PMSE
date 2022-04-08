package nl.avans.movieapp.ui.movie.addToList;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import nl.avans.movieapp.R;
import nl.avans.movieapp.controller.AddMovieController;
import nl.avans.movieapp.controller.CreateMovieListController;
import nl.avans.movieapp.controller.MovieListSpecController;
import nl.avans.movieapp.controller.MovieListsController;
import nl.avans.movieapp.domain.Movie;
import nl.avans.movieapp.domain.MovieList;
import nl.avans.movieapp.service.MovieAPI;
import nl.avans.movieapp.ui.gallery.CreateMovieListDialog;
import nl.avans.movieapp.ui.movielist.MoviePageViewModel;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddMovieToListDialog extends DialogFragment implements AddMovieToListAdapter.OnMovieListSelectionListener {
    private final String LOG_TAG = this.getClass().getSimpleName();
    private AddToMovieListViewModel addToMovieListViewModel;
    private ArrayList<MovieList> movieLists = new ArrayList<>();
    private AddMovieToListAdapter addMovieToListAdapter;
    private RecyclerView recyclerView;
    private Movie mMovie;
    private MovieListsController.MovieListsControllerListener listener;

    public AddMovieToListDialog(Movie movie) {
        this.mMovie = movie;
        this.listener = listener;
    }

    // Use this instance of the interface to deliver action events
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("Test", "onCreate called");

        View root = inflater.inflate(R.layout.dialog_select_movielist, container);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(inflater.inflate(R.layout.dialog_select_movielist, container))
                .setMessage(R.string.dialog_add_to_movielist_message)
                .setTitle(R.string.dialog_add_to_movielist_title)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        AddMovieToListDialog.this.getDialog().cancel();
                    }
                });

        recyclerView = root.findViewById(R.id.add_movie_to_list_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        addMovieToListAdapter = new AddMovieToListAdapter(this);

        recyclerView.setAdapter(addMovieToListAdapter);

        addToMovieListViewModel = new ViewModelProvider(this).get(AddToMovieListViewModel.class);
        addToMovieListViewModel.getMovieLists().observe(getViewLifecycleOwner(), new Observer<ArrayList<MovieList>>() {
            @Override
            public void onChanged(@Nullable ArrayList<MovieList> givenMovieLists) {
                Log.d(LOG_TAG, "onChanged");
                movieLists = givenMovieLists;
                addMovieToListAdapter.setMovieLists(movieLists);
            }
        });

        return root;
    }

    @Override
    public void onMovieListSelected(int position) {
        Log.d(LOG_TAG, mMovie.getId() + " " + movieLists.get(position).getId());
        AddMovieController specController = new AddMovieController();
        specController.addMovieToList(mMovie.getId(), movieLists.get(position).getId());
        Log.d("AddMovieToList", "Het werkt"+ mMovie.getId() + " "+  movieLists.get(position).getId());
        AddMovieToListDialog.this.getDialog().cancel();
    }

}
